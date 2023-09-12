import { Component, OnInit } from '@angular/core';
import { HttpEventType, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { FileUploadService } from 'src/app/services/file-upload.service';
import * as xml2js from 'xml2js';


@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrls: ['./file-upload.component.scss']
})
export class FileUploadComponent implements OnInit {

  files?: File[];
  progress = 0;
  message = '';

  fileName = 'Click aqui para selecionar o arquivo';
  fileInfos?: Observable<any>;
  private agentes!: any[];
  showProgressaBar: boolean = false; 

  constructor(private uploadService: FileUploadService) { }

  ngOnInit(): void { }

  selecioneArquivo(event: any): void {
    if (event.target.files && event.target.files[0]) {
      const files: File[] = event.target.files;
      this.agentes = [];
      for (let file of files) {
        this.files = files;

        const reader = new FileReader();
        reader.onload = () => {
          const xmlString = reader.result as string;
          this.xmlToJson(xmlString).then(data => {
            data.arquivo = file.name;
            console.log('Dados do JSON:', data);

            this.removerDadosPrecoMedio(data);

          }).catch(error => {
            console.error('Erro ao converter XML para JSON:', error);
          });
          this.showProgressaBar = true;
        };
        reader.readAsText(file);
      }


    } else {
      this.fileName = 'Selecione o Arquivo';
    }
  }

  upload(arquivo: string): void {
    this.progress = 0;
    this.message = "";

    if (this.files) {
      this.showProgressaBar = true;
      const agenteUpload: any = this.agentes.filter((a: any) => a.arquivo == arquivo)[0];
      console.log(agenteUpload);

      this.uploadService.upload(agenteUpload).subscribe(
        (event: any) => {
          if (event.type === HttpEventType.UploadProgress) {
            this.progress = Math.round(100 * event.loaded / event.total);
          }
        },
        (err: any) => {
          console.log(err);
          this.progress = 0;

          if (err.error && err.error.message) {
            this.message = err.error.message;
          } else {
            this.message = 'Erro ao fazer o upload do arquivo';
          }

          this.files = undefined;
          this.showProgressaBar = false;
        });
    }
  }

  private xmlToJson(xmlString: string): Promise<any> {
    return new Promise((resolve, reject) => {
      const parser = new xml2js.Parser({ explicitArray: false, mergeAttrs: true });
      parser.parseString(xmlString, (error: any, result: any) => {
        if (error) {
          reject(error);
        } else {
          resolve(result);
        }
      });
    });
  }

  private removerDadosPrecoMedio(dados: any): void {
    if (!dados) {
      return;
    }

    if (typeof dados.agentes.agente === 'object' && !Array.isArray(dados.agentes.agente)) {
      dados.agentes.agente = [dados.agentes.agente];
    }

    for (let a of dados.agentes.agente) {
      a.regiao.forEach((r: any) => {
        delete r.precoMedio;
      });
    }

    this.agentes.push(dados);
    console.log(this.agentes);
  }


  ocultarProgressBar(event: any): void {
    this.showProgressaBar = false;
  }
}

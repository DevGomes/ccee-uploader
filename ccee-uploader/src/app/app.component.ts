import { FileUploadService } from './services/file-upload.service';
import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  
  public codigoRegiao = '';
  public dadosConsolidados: any = [];

  constructor(private fileService: FileUploadService) { }

  buscarDadosConsolidadoRegiao(): void {
    this.fileService.getDadosConsolidados(this.codigoRegiao).subscribe(result => {
      console.log(result);
      this.dadosConsolidados = result;
    });
  }

}

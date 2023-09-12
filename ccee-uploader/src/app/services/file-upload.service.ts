import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FileUploadService {

  private baseUrl = 'http://localhost:8081/api';

  constructor(private http: HttpClient) { }

  upload(agenteUpload: any[]): Observable<HttpEvent<any>> {
    const req = new HttpRequest('POST', `${this.baseUrl}/agentes`, agenteUpload, {
      reportProgress: true,
      responseType: 'json'
    });

    return this.http.request(req);
  }

  getDadosConsolidados(regiao: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/agentes/${regiao}`);
  }
}

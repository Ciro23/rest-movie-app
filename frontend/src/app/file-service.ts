import {Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";

@Injectable({providedIn: "root"})
export class FileService {

  constructor(private httpClient: HttpClient) {}

  /**
   * Downloads the .xlsx file with list of all the users.
   */
  downloadXlsxFile(fileUrl: string, fileName: string, httpParams?: HttpParams) {
    this.httpClient.get(fileUrl, { responseType: 'blob', params: httpParams }
    ).subscribe((data) => {
      this.downloadFile(fileName, data, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    });
  }

  /**
   * Downloads the .pdf file with the details of a user, given its id.
   */
  downloadPdfFile(fileUrl: string, fileName: string) {
    this.httpClient.get(fileUrl, { responseType: 'blob' }
    ).subscribe((data) => {
      this.downloadFile(fileName, data, "application/pdf");
    });
  }

  /**
   * Callback used to process the download of a file after the
   * GET request has been made.
   * @param fileName The name assigned to the downloaded file.
   * @param data The response body of the GET request, containing the file bytes.
   * @param contentType E.g. "application/pdf", "application/json"
   * @private
   */
  private downloadFile(fileName: string, data: Blob, contentType: string) {
    const blob = new Blob(
      [data],
      { type: contentType }
    );

    // Explicitly casting window as any to avoid TypeScript errors
    const url = (window as any).URL.createObjectURL(blob);

    const element = document.createElement('a');
    element.href = url;
    element.download = fileName;
    element.click();

    // Cleanup
    (window as any).URL.revokeObjectURL(url);
  }
}

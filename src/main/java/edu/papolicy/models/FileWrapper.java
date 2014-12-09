package edu.papolicy.models;

//import org.springframework.web.multipart.MultipartFile;

public class FileWrapper {
    private File fileObj;
    private Batch batchObj;
    //private MultipartFile data;


    public File getFileObj() {
        return fileObj;
    }

    public void setFileObj(File fileObj) {
        this.fileObj = fileObj;
    }

    public Batch getBatchObj() {
        return batchObj;
    }

    public void setBatchObj(Batch batchObj) {
        this.batchObj = batchObj;
    }

    //public MultipartFile getData() {
    //    return data;
    //}

    //public void setData(MultipartFile data) {
    //    this.data = data;
    //}
}

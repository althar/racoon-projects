package racoonsoft.languagebox.structure;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;

public class MultipartFilesStructure
{

    private CommonsMultipartFile [] files;

    public CommonsMultipartFile[] getFiles() {
        return files;
    }

    public void setFiles( CommonsMultipartFile[] files )
    {
        this.files = files;
    }
}

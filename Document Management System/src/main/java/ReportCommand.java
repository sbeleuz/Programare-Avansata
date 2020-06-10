import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReportCommand extends Command {
    private String type;
    private String path;

    public ReportCommand(Catalog catalog, String type, String path) {
        this.catalog = catalog;
        this.type = type;
        this.path = path;
    }

    public void executeCommand() throws IOException {
        if (type.equals("html")) htmlReport();
        else if (type.equals("word")) wordReport();
    }

    public void htmlReport() throws IOException {
        String htmlReport = "<!DOCTYPE html><head><title>HTML Report</title></head><body>" +
                catalog.toString() +
                "</body></html>";
        Files.writeString(Paths.get(path), htmlReport);
    }

    public void wordReport() throws IOException {
        // blank Document
        XWPFDocument document = new XWPFDocument();

        // write the Document in file system
        try (FileOutputStream out = new FileOutputStream(new File(path))) {
            // create Paragraph
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setText(catalog.toString());

            // write paragraph to document
            document.write(out);
        }
    }
}

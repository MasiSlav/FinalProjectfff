package com.newsPub.newsPub.controller;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.newsPub.newsPub.entity.NewsEnt;
import com.newsPub.newsPub.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadController {
    @Autowired
    private NewsRepository newsRepository;

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public @ResponseBody
    String provideUploadInfo() {
        return "Вы можете загружать файл с использованием того же URL.";
    }

    public void deleteArchive(String filePath) {
        File myFile = new File(filePath);
        myFile.delete();
    }

    public int addToDb(ZipInputStream zin, String category) {
        String line;
        String title = "";
        String content = "";
        int counter = 0;
        try {
            Scanner in = new Scanner(zin, "UTF-8");
            while (in.hasNextLine()) {
                line = in.nextLine();
                if (counter == 0) {
                    title = line;
                } else {
                    content += line + "\n";
                }
                counter++;
            }
            in.close();
            if (counter < 2) {
                return counter;
            }
            NewsEnt news = new NewsEnt(title, category, content);
            newsRepository.save(news);
        } catch (Exception ex) {
            ex.getMessage();
        }
        return counter;
    }


    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody
    String handleFileUpload(@RequestParam("category") String category,
                            @RequestParam("file") MultipartFile file) {
        int checkCounter;
        if (!file.isEmpty()) {
            try {
                if (!Objects.equals(file.getContentType(), "application/x-zip-compressed"))
                    return "Файл не является zip архивом. Пожалуйста загрузите файл с расширением zip";
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File("Uploaded" + File.separator + Objects.requireNonNull(file.getOriginalFilename()))));
                stream.write(bytes);
                stream.close();
                try (ZipInputStream zin = new ZipInputStream(new FileInputStream("Uploaded" + File.separator + Objects.requireNonNull(file.getOriginalFilename())))) {
                    ZipEntry entry = zin.getNextEntry();
                    assert entry != null;
                    if (!entry.getName().equals("article.txt")) {
                        deleteArchive("Uploaded" + File.separator + Objects.requireNonNull(file.getOriginalFilename()));
                        return "Файл внутри не article.txt";
                    }
                    checkCounter = addToDb(zin, category);
                    if (checkCounter < 2) {
                        deleteArchive("Uploaded" + File.separator + Objects.requireNonNull(file.getOriginalFilename()));
                        return "Текстовый файл не по формату (содержит меньше 2 строк)";
                    }
                } catch (Exception ex) {
                    return ex.getMessage();
                }
                deleteArchive("Uploaded" + File.separator + Objects.requireNonNull(file.getOriginalFilename()));
                return "Вы удачно загрузили  " + file.getOriginalFilename() + " в " + category + "-uploaded !";
            } catch (Exception e) {
                return "Вам не удалось загрузить " + " => " + e.getMessage();
            }
        } else {
            return "Вам не удалось загрузить " + " потому что файл пустой.";
        }
    }

}

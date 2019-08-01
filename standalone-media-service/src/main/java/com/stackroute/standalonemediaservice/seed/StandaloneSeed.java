package com.stackroute.standalonemediaservice.seed;

import com.stackroute.standalonemediaservice.domain.Cast;
import com.stackroute.standalonemediaservice.domain.Crew;
import com.stackroute.standalonemediaservice.domain.StandaloneMedia;
import com.stackroute.standalonemediaservice.repository.StandaloneRepository;
import com.stackroute.standalonemediaservice.service.StandaloneServiceImpl;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StandaloneSeed implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    StandaloneRepository mediaRepository;
    @Autowired
    StandaloneServiceImpl mediaService;
    private Crew crew;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        try {
            FileInputStream file = new FileInputStream(new File("standalone.xlsx"));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            // Traversing over each row of XLSX file
//            rowIterator.next();//Skipping 1st line
            System.out.println("SEED DATA .............................................");
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                // For each row, iterate through each columns
                Iterator<Cell> cellIterator = row.cellIterator();
                for (int i = 1; cellIterator.hasNext(); i++) {
                    StandaloneMedia media=new StandaloneMedia();
                    for (int j = 0; j <= sheet.getLeftCol(); j++) {
                        int count =100;
                        media.setMediaTitle(workbook.getSheetAt(0).getRow(i).getCell(j + 0).toString());
                        media.setMediaCategory(workbook.getSheetAt(0).getRow(i).getCell(j + 1).toString());
                        media.setMediaSynopsis(workbook.getSheetAt(0).getRow(i).getCell(j + 2).toString());
                        List<String> genres=new ArrayList<>();
                        genres.add(workbook.getSheetAt(0).getRow(i).getCell(j + 3).toString());
                        media.setMediaGenre(genres);
                        media.setMediaLanguage(workbook.getSheetAt(0).getRow(i).getCell(j + 4).toString());
                        media.setMediaReleaseDate(workbook.getSheetAt(0).getRow(i).getCell(j + 5).getDateCellValue());
                        media.setMediaPosterUrl(workbook.getSheetAt(0).getRow(i).getCell(j + 6).toString());
                        media.setMediaStudioName(workbook.getSheetAt(0).getRow(i).getCell(j + 7).toString());
                        List<Crew> crews=new ArrayList<>();
                        Crew crew=new Crew();
                        crew.setCrewName(workbook.getSheetAt(0).getRow(i).getCell(j + 8).toString());
                        crew.setCrewRole(workbook.getSheetAt(0).getRow(i).getCell(j + 9).toString());
                        crews.add(crew);
                        media.setMediaCrew(crews);
                        List<Cast> casts=new ArrayList<>();
                        Cast cast=new Cast();
                        cast.setScreenName(workbook.getSheetAt(0).getRow(i).getCell(j + 10).toString());
                        cast.setRealName(workbook.getSheetAt(0).getRow(i).getCell(j + 11).toString());
                        casts.add(cast);
                        media.setMediaCast(casts);
                        media.setMediaTrailerUrl(workbook.getSheetAt(0).getRow(i).getCell(j + 12).toString());
                        media.setMediaType(workbook.getSheetAt(0).getRow(i).getCell(j + 13).toString());
                        mediaRepository.save(media);
                        System.out.println(media);
                    }
                }
            }
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelExporter implements ActionListener {
	
	ViewController expData;
	
	public ExcelExporter(ViewController vc) {
		expData = vc;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel file", "*.xls");
		JFileChooser fc = new JFileChooser();
		fc.addChoosableFileFilter(filter);
		fc.setAcceptAllFileFilterUsed(false);

        if ( fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION ) {
        	File file = fc.getSelectedFile();
        	if(!file.getName().endsWith(".txt")) {
        		String str = file.getPath();
        		fc.setSelectedFile(new File(str + ".xls"));
        	}
        	Workbook wb = new HSSFWorkbook();
        	Sheet walletsSheet = wb.createSheet("Кошельки");
            Sheet incomeSheet = wb.createSheet("Доходы");
            Sheet expenseSheet = wb.createSheet("Расходы");

            int i = 0;
            for(ComponentManager cm: expData.getWallets()) {
            	Row row = walletsSheet.createRow((short)i++);
                Cell cell = row.createCell(0);
            	cell.setCellValue(cm.getName());
            	cell = row.createCell(1);
            	cell.setCellValue(0);
            }
            
            i = 0;
            for(ComponentManager cm: expData.getIncome()) {
            	Row row = incomeSheet.createRow((short)i++);
                Cell cell = row.createCell(0);
            	cell.setCellValue(cm.getName());
            	cell = row.createCell(1);
            	cell.setCellValue(0);
            }
            
            i = 0;
            for(ComponentManager cm: expData.getExpense()) {
            	Row row = expenseSheet.createRow((short)i++);
                Cell cell = row.createCell(0);
            	cell.setCellValue(cm.getName());
            	cell = row.createCell(1);
            	cell.setCellValue(0);
            }
            
            try (FileOutputStream fileOut = new FileOutputStream(fc.getSelectedFile())) {
            	wb.write(fileOut);
            }
            catch ( IOException e ) {
                System.out.println("Всё погибло!");
            }
        }
	}

}

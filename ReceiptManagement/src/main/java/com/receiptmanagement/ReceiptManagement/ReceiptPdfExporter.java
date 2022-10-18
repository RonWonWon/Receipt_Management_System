package com.receiptmanagement.ReceiptManagement;

import java.awt.Color;
import java.io.IOException;
import java.io.ByteArrayOutputStream;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.receiptmanagement.ReceiptManagement.RECEIPT.RECEIPT;


public class ReceiptPdfExporter {
    private void writeTableHeader(PdfPTable t1,PdfPTable t2,PdfPTable t3,PdfPTable t4,PdfPTable t5){
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.LIGHT_GRAY);
        cell.setPhrase(new Phrase("Name"));
        t1.addCell(cell);
        cell.setPhrase(new Phrase("Mobile"));
        t1.addCell(cell);
        cell.setPhrase(new Phrase("Amount (in Rupees)"));
        t2.addCell(cell);
        cell.setPhrase(new Phrase("Pan No"));
        t3.addCell(cell);
        cell.setPhrase(new Phrase("Email"));
        t3.addCell(cell);
        cell.setPhrase(new Phrase("Description"));
        t4.addCell(cell);
        cell.setPhrase(new Phrase("Created At"));
        t5.addCell(cell);
    }

    private void writeTableData(PdfPTable t1,PdfPTable t2,PdfPTable t3,PdfPTable t4,PdfPTable t5, RECEIPT curReceipt){
        t1.addCell(curReceipt.getName());
        t1.addCell(curReceipt.getMobile());
        t2.addCell(curReceipt.getAmount().toString());
        t3.addCell(curReceipt.getPan_no());
        t3.addCell(curReceipt.getEmail());
        t4.addCell(curReceipt.getDescription());
        t5.addCell(curReceipt.getCreated_at());
    }

    protected void export(HttpServletResponse response, RECEIPT curReceipt) throws DocumentException, IOException{
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER_BOLD);
        font.setSize(18);
        font.setColor(Color.BLACK);

        Paragraph p = new Paragraph("Receipt", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p);
        p = new Paragraph(" ",font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p);

        PdfPTable table1 = new PdfPTable(2);
        PdfPTable table2 = new PdfPTable(1);
        PdfPTable table3 = new PdfPTable(2);
        PdfPTable table4 = new PdfPTable(1);
        PdfPTable table5 = new PdfPTable(1);

        table1.setWidthPercentage(100F);
        table2.setWidthPercentage(100F);
        table3.setWidthPercentage(100F);
        table4.setWidthPercentage(100F);
        table5.setWidthPercentage(100F);
        
        writeTableHeader(table1,table2,table3,table4,table5);
        writeTableData(table1,table2,table3,table4,table5, curReceipt);

        document.add(table1);
        document.add(p);

        document.add(table2);
        document.add(p);

        document.add(table3);
        document.add(p);

        document.add(table4);
        document.add(p);

        document.add(table5);
        document.add(p);

        document.close();
    }

    protected void export(ByteArrayOutputStream outputStream, RECEIPT curReceipt) throws DocumentException, IOException{
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, outputStream);

        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER_BOLD);
        font.setSize(18);
        font.setColor(Color.BLACK);

        Paragraph p = new Paragraph("Receipt", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p);
        p = new Paragraph(" ",font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p);

        PdfPTable table1 = new PdfPTable(2);
        PdfPTable table2 = new PdfPTable(1);
        PdfPTable table3 = new PdfPTable(2);
        PdfPTable table4 = new PdfPTable(1);
        PdfPTable table5 = new PdfPTable(1);

        table1.setWidthPercentage(100F);
        table2.setWidthPercentage(100F);
        table3.setWidthPercentage(100F);
        table4.setWidthPercentage(100F);
        table5.setWidthPercentage(100F);
        
        writeTableHeader(table1,table2,table3,table4,table5);
        writeTableData(table1,table2,table3,table4,table5, curReceipt);

        document.add(table1);
        document.add(p);

        document.add(table2);
        document.add(p);

        document.add(table3);
        document.add(p);

        document.add(table4);
        document.add(p);

        document.add(table5);
        document.add(p);

        document.close();
    }
}

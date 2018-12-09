package application;
import java.io.FileInputStream;
import java.io.FileOutputStream;
//import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
///import java.util.Arrays;
import java.util.List;
//import java.util.Vector;
import java.util.logging.Level;

import java.util.logging.Logger;

//import javax.swing.GroupLayout.Alignment;
//import javax.swing.text.TabExpander;
//import javax.swing.text.TabableView;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.IOUtils;
//import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;


import application.entities.Motorbike;
import application.entities.Order;
import application.entities.OrderDetail;



public class FormHD {   

 private Order order;
 private XWPFDocument doc;
 
    public FormHD(Order order) {
	super();
	this.order = order;
	
	
}


	public void formOrder(String url) throws InvalidFormatException {

		try {

            //Bước 1: Khởi tạo đối tượng để sinh ra file word

            XWPFDocument document = new XWPFDocument();

            //Bước 2: Tạo tiêu đề bài viết
         
            XWPFParagraph titleGraph2 = document.createParagraph();           
            titleGraph2.setAlignment(ParagraphAlignment.RIGHT);
            String title2 = order.getOrderID().toString();  
            XWPFRun titleRun2 = titleGraph2.createRun();
            titleRun2.setBold(true);
            titleRun2.setText(title2);
            

            XWPFParagraph titleGraph = document.createParagraph();
            titleGraph.setAlignment(ParagraphAlignment.CENTER);
            String title = "HÓA ĐƠN BÁN LẺ";           
            XWPFRun titleRun = titleGraph.createRun();
            titleRun.setBold(true);
            titleRun.setText(title);
            
            
           XWPFParagraph titleGraph1 = document.createParagraph();
           titleGraph1.setAlignment(ParagraphAlignment.CENTER);
           String title1 = "Ngày" + "\n\n"  + order.getDate().getDayOfMonth()+ "\n\n" + "Tháng" + "\n\n" + order.getDate().getMonthValue()+"\n\n" + "Năm" + "\n\n" + order.getDate().getYear();            
           XWPFRun titleRun1 = titleGraph1.createRun();
           titleRun1.setBold(true);
           titleRun1.setText(title1);

           XWPFParagraph para11 = document.createParagraph();
           String dataPara11 = "-----------------------------------------------------------------------------------------------------------------------------------------";      
           XWPFRun para11Run = para11.createRun();
           para11Run.setText(dataPara11);

            //Bước 3: Tạo đoạn văn bản 1 trong tài liệu

            XWPFParagraph para1 = document.createParagraph();        
            String dataPara1 = "Đơn vị bán hàng: CÔNG TY TNHH HOÀNG CẦU";
            XWPFRun para1Run = para1.createRun();
            para1Run.setText(dataPara1);
                       
            XWPFParagraph para2 = document.createParagraph();
            String dataPara2 = "Mã số thuế: 301660692";         
            XWPFRun para2Run = para2.createRun();
            para2Run.setText(dataPara2);
            
            XWPFParagraph para3 = document.createParagraph();
            String dataPara3 = "Địa chỉ: 333 Lê Văn Sỹ, phường 13, Quận 3, TPHCM";         
            XWPFRun para3Run = para3.createRun();
            para3Run.setText(dataPara3);
            
            XWPFParagraph para4 = document.createParagraph();
            String dataPara4 = "Điện thoại: 02723648888";         
            XWPFRun para4Run = para4.createRun();
            para4Run.setText(dataPara4);

            XWPFParagraph para5 = document.createParagraph();
            String dataPara5 = "-----------------------------------------------------------------------------------------------------------------------------------------";      
            XWPFRun para5Run = para5.createRun();
            para5Run.setText(dataPara5);
            
            XWPFParagraph para6 = document.createParagraph();
            String dataPara6 = "Họ Tên Khách hàng : " + order.getCustomer().getLastName()+ " " +order.getCustomer().getFirstName();      
            XWPFRun para6Run = para6.createRun();
            para6Run.setText(dataPara6);
            
            XWPFParagraph para7 = document.createParagraph();
            String dataPara7 = "Điện thoại Khách hàng: " + order.getCustomer().getPhoneNumber();      
            XWPFRun para7Run = para7.createRun();
            para7Run.setText(dataPara7);
            
            XWPFParagraph para8 = document.createParagraph();
            String dataPara8 = "Địa chỉ Khách hàng: " + order.getCustomer().getAddress();      
            XWPFRun para8Run = para8.createRun();
            para8Run.setText(dataPara8);
            
            XWPFParagraph para9 = document.createParagraph();
            String dataPara9 = "Hình thức thanh toán: Thanh toán trực tiếp";      
            XWPFRun para9Run = para9.createRun();
            para9Run.setText(dataPara9);
                                  
            XWPFTable table = document.createTable();
          //  CTTblWidth width = table.getCTTbl().addNewTblPr().addNewTblW();

           // width.setType(STTblWidth.DXA);
         //  width.setW(BigInteger.valueOf(1500));
    
            XWPFTableRow tableRowOne = table.getRow(0);
            tableRowOne.getCell(0).setText("STT");
            tableRowOne.addNewTableCell().setText("Tên Sản Phẩm" + Space(100));
            tableRowOne.addNewTableCell().setText("Số lượng" + Space(50));
            tableRowOne.addNewTableCell().setText("Màu sắc" + Space(50));
            tableRowOne.addNewTableCell().setText("Thành tiền" + Space(50));
                      
           List<OrderDetail> details = order.getListItems();
           for (int m = 0; m < details.size();m++) {
        	   
        	Motorbike b = details.get(m).getMotorbike();
        	
        	 String stt = String.valueOf(m+1);
       	  	 XWPFTableRow tableRowTwo = table.createRow();
             tableRowTwo.getCell(0).setText(stt);
             String name = b.getProductName();
             tableRowTwo.getCell(1).setText(name);
             String qty = String.valueOf(new Long(details.get(m).getQuantity()));
             tableRowTwo.getCell(2).setText(qty);
             String col = details.get(m).getColor();
             tableRowTwo.getCell(3).setText(col);
             long m0 = (long) details.get(m).getUnitPrice();
             String x = String.format("%,d",m0);             
             tableRowTwo.getCell(4).setText(x + "VND");		
		}

     
           List<OrderDetail> details1 = order.getListItems();
           long y= 0;
           for (int ie = 0; ie < details1.size();ie++) {
        	   
        		   y+= details1.get(ie).getUnitPrice();
        		         		  
           }
           String x = String.format("%,d",y);
           XWPFParagraph para13 = document.createParagraph();
           String dataPara13 ="Tổng tiền hàng:"+"\n\n"+x +"VND";      
           XWPFRun para13Run = para13.createRun();
           para13Run.setText(dataPara13);
        		          	              
           long tt=order.getTotalVAT();
           String x1 = String.format("%,d",tt);
           XWPFParagraph para14 = document.createParagraph();
           String dataPara14 = "Tiền thuế phải trả :"+"\n\n"+x1 +"VND"+"(Thuế suất 10%)";      
           XWPFRun para14Run = para14.createRun();
           para14Run.setText(dataPara14);
                   
           XWPFParagraph para10 = document.createParagraph();
           long T = y+tt;
           String TT = String.format("%,d"+ "VND",T);
           String dataPara10 ="Tổng cộng tiền thanh toán:"+"\n\n" +TT;      
           XWPFRun para10Run = para10.createRun();
           para10Run.setText(dataPara10);
           
  
           String tienchu =numberToString(T);
           XWPFParagraph para12 = document.createParagraph();
           String dataPara12 = "Số tiền viết bằng chữ:"+"\n\n"+tienchu + ".";      
           XWPFRun para12Run = para12.createRun();
           para12Run.setText(dataPara12);
 
           XWPFParagraph para16 = document.createParagraph();
           String dataPara16 = "-----------------------------------------------------------------------------------------------------------------------------------------";      
           XWPFRun para16Run = para16.createRun();
           para16Run.setText(dataPara16);
            
           XWPFParagraph titleGraph3 = document.createParagraph();           
           titleGraph3.setAlignment(ParagraphAlignment.LEFT);
           String title3 = "Người mua hàng"+"\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"+"Người bán hàng" ;  
           XWPFRun titleRun3 = titleGraph3.createRun();
           titleRun3.setBold(true);
           titleRun3.setText(title3); 
          
           XWPFParagraph titleGraph4 = document.createParagraph();           
           titleGraph4.setAlignment(ParagraphAlignment.LEFT);
           String title4 = "(Kí, ghi rõ họ tên)"+"\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"+"Kí, đóng dấu ghi rõ họ tên";  
           XWPFRun titleRun4 = titleGraph4.createRun();
           titleRun4.setBold(true);
           titleRun4.setText(title4);
           
//           XWPFDocument doc = new XWPFDocument();   
//           FileInputStream is = new FileInputStream("C:\\Users\\HOANG-QUI\\Pictures\\1.jpg");
//           doc.addPictureData(IOUtils.toByteArray(is), Document.PICTURE_TYPE_JPEG);


//           XWPFParagraph title5 = doc.createParagraph();    
//           XWPFRun run = title5.createRun();
//        //   run.setText("Fig.1 A Natural Scene");
//           run.setBold(true);
//           title5.setAlignment(ParagraphAlignment.LEFT);
           
//           XWPFDocument doc = new XWPFDocument();
//
//           XWPFParagraph title100 = doc.createParagraph();    
//           XWPFRun run = title100.createRun();
//           run.setText("Fig.1 A Natural Scene");
//           run.setBold(true);
//           title100.setAlignment(ParagraphAlignment.CENTER);
//
//           String imgFile = "C:\\Users\\HOANG-QUI\\Pictures\\1.jpg";
//           FileInputStream is = new FileInputStream(imgFile);
//           run.addBreak();
//           run.addPicture(is, XWPFDocument.PICTURE_TYPE_JPEG, imgFile, Units.toEMU(300), Units.toEMU(200)); // 200x200 pixels
//           is.close();
         
            
            
            
            
            
            
            //Bước 4: Ghi dữ liệu ra file word         
            String s= order.getOrderID().toString() +"_"+ order.getDate().toString()+"_HoaDon" ;
            String urlfile =url + s +".docx";
            FileOutputStream out = new FileOutputStream(urlfile);

            document.write(out);

            out.close();

            document.close();

        } catch (IOException ex) {

            Logger.getLogger(FormHD.class.getName()).log(Level.SEVERE, null, ex);

        }

    }
	 public static String formatNumberForRead(double number) {
	        NumberFormat nf = NumberFormat.getInstance();
	        String temp = nf.format(number);
	        String strReturn = "";
	        int slen = temp.length();
	        for (int i = 0; i < slen; i++) {
	            if (String.valueOf(temp.charAt(i)).equals("."))
	                break;
	            else if (Character.isDigit(temp.charAt(i))) {
	                strReturn += String.valueOf(temp.charAt(i));
	            }
	        }
	        return strReturn;

	    }
	    public static String numberToString(long number) {
	        String sNumber = String.valueOf(number);//formatNumberForRead(number);
	        // Tao mot bien tra ve
	        String sReturn = "";
	        // Tim chieu dai cua chuoi
	        int iLen = sNumber.length();
	        // Lat nguoc chuoi nay lai
	        String sNumber1 = "";
	        for (int i = iLen - 1; i >= 0; i--) {
	            sNumber1 += sNumber.charAt(i);
	        }
	        // Tao mot vong lap de doc so
	        // Tao mot bien nho vi tri cua sNumber
	        int iRe = 0;
	        do {
	            // Tao mot bien cat tam
	            String sCut = "";
	            if (iLen > 3) {
	                sCut = sNumber1.substring((iRe * 3), (iRe * 3) + 3);
	                sReturn = Read(sCut, iRe) + sReturn;
	                iRe++;
	                iLen -= 3;
	            } else {
	                sCut = sNumber1.substring((iRe * 3), (iRe * 3) + iLen);
	                sReturn = Read(sCut, iRe) + sReturn;
	                break;
	            }
	        } while (true);
	        if(sReturn.length() > 1){
	            sReturn = sReturn.substring(0,1).toUpperCase() + sReturn.substring(1);
	        }
	        sReturn = sReturn + "đồng";
	        return sReturn;
	    }

	    // Khoi tao ham Read
	    public static String Read(String sNumber, int iPo) {
	        // Tao mot bien tra ve
	        String sReturn = "";
	        // Tao mot bien so
	        String sPo[] = { "", "ngàn" + " ",
	                "triệu" + " ", "tỷ" + " " };
	        String sSo[] = { "không" + " ", "một" + " ",
	                "hai" + " ", "ba" + " ",
	                "bốn" + " ", "năm" + " ",
	                "sáu" + " ", "bảy" + " ",
	                "tám" + " ", "chín" + " " };
	        String sDonvi[] = { "", "mươi" + " ",
	                "trăm" + " " };
	        // Tim chieu dai cua chuoi
	        int iLen = sNumber.length();
	        // Tao mot bien nho vi tri doc
	        int iRe = 0;
	        // Tao mot vong lap de doc so
	        for (int i = 0; i < iLen; i++) {
	            String sTemp = "" + sNumber.charAt(i);
	            int iTemp = Integer.parseInt(sTemp);
	            // Tao mot bien ket qua
	            String sRead = "";
	            // Kiem tra xem so nhan vao co = 0 hay ko
	            if (iTemp == 0) {
	                switch (iRe) {
	                case 0:
	                    break;// Khong lam gi ca
	                case 1: {
	                    if (Integer.parseInt("" + sNumber.charAt(0)) != 0) {
	                        sRead = "lẻ" + " ";
	                    }
	                    break;
	                }
	                case 2: {
	                    if (Integer.parseInt("" + sNumber.charAt(0)) != 0
	                            && Integer.parseInt("" + sNumber.charAt(1)) != 0) {
	                        sRead = "không trăm" + " ";
	                    }
	                    break;
	                }
	                }
	            } else if (iTemp == 1) {
	                switch (iRe) {
	                case 1:
	                    sRead = "mười" + " ";
	                    break;
	                default:
	                    sRead = "một" + " " + sDonvi[iRe];
	                    break;
	                }
	            } else if (iTemp == 5) {
	                switch (iRe) {
	                case 0: {
	                    if(sNumber.length() <= 1){
	                        sRead = "năm" + " ";
	                    }
	                    else if (Integer.parseInt("" + sNumber.charAt(1)) != 0) {
	                        sRead = "lăm" + " ";
	                    } else
	                        sRead = "năm" + " ";
	                    break;
	                }
	                default:
	                    sRead = sSo[iTemp] + sDonvi[iRe];
	                }
	            } else {
	                sRead = sSo[iTemp] + sDonvi[iRe];
	            }

	            sReturn = sRead + sReturn;
	            iRe++;
	        }
	        if (sReturn.length() > 0) {
	            sReturn += sPo[iPo];
	        }

	        return sReturn;
	    }


	  public static String repeat(String s, int n) {
	    if(s == null) {
	        return null;
	    }
	    final StringBuilder sb = new StringBuilder();
	    for(int i = 0; i < n; i++) {
	        sb.append(s);
	    }
	    return sb.toString();
	  }
	  String Space(int n) {
		  String y = " ";
		  for (int i = 0; i < n; i++) {
			y+=" ";
		}
		return y;
	  }

}

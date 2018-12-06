package application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.IOUtils;
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

public class HopDong {
	private Order order;
	 private XWPFDocument doc;
	 
	    public HopDong(Order order) {
		super();
		this.order = order;
	    }
	    public void inHopDong() {
	    	for (int i = 0; i < order.getListItems().size(); i++) {
	    		try {
					formHopDong(order.getListItems().get(i), order.getListItems().get(i).getOrderDetailID());
				} catch (InvalidFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}   		
				
			}
	    }
	    public void formHopDong(OrderDetail dt, String n) throws InvalidFormatException {

	        try {

	            //Bước 1: Khởi tạo đối tượng để sinh ra file word

	            XWPFDocument document = new XWPFDocument();
	            
	 

	            //Bước 2: Tạo tiêu đề bài viết

	            XWPFParagraph titleGraph = document.createParagraph();
	            titleGraph.setAlignment(ParagraphAlignment.CENTER);
	            String title = "CỘNG HÒA XÃ HỘI CHỦ NGHĨA VIỆT NAM";           
	            XWPFRun titleRun = titleGraph.createRun();
	            titleRun.setBold(true);
	            titleRun.setText(title);
	            
	            XWPFParagraph titleGraph1 = document.createParagraph();
	            titleGraph1.setAlignment(ParagraphAlignment.CENTER);
	            String title1 = "Độc lập - Tự do - Hạnh phúc";           
	            XWPFRun titleRun1 = titleGraph1.createRun();
	            titleRun1.setBold(true);
	            titleRun1.setText(title1);
	            
	            XWPFParagraph titleGraph2 = document.createParagraph();
	            titleGraph2.setAlignment(ParagraphAlignment.CENTER);
	            String title2 = "---------------------";           
	            XWPFRun titleRun2 = titleGraph2.createRun();
	            titleRun2.setBold(true);
	            titleRun2.setText(title2);
	            
	            XWPFParagraph titleGraph3 = document.createParagraph();
	            titleGraph3.setAlignment(ParagraphAlignment.CENTER);
	            String title3 = "HỢP ĐỒNG MUA BÁN XE MÁY";           
	            XWPFRun titleRun3 = titleGraph3.createRun();
	            titleRun3.setBold(true);
	            titleRun3.setText(title3);
	            
	            XWPFParagraph para1 = document.createParagraph();        
	            String dataPara1 = "Hôm nay,"+"Ngày"+ "\n\n"+ order.getDate().getDayOfMonth()+"\n\n" + "Tháng"+"\n\n"+order.getDate().getMonthValue()+"\n\n"+"Năm"+"\n\n"+order.getDate().getYear()+"tại TPHCM , chúng tôi gồm có: ";
	            XWPFRun para1Run = para1.createRun();
	            para1Run.setText(dataPara1);
	            
	            XWPFParagraph titleGraph4 = document.createParagraph();
	            titleGraph4.setAlignment(ParagraphAlignment.LEFT);
	            String title4 = "BÊN BÁN";           
	            XWPFRun titleRun4 = titleGraph4.createRun();
	            titleRun4.setBold(true);
	            titleRun4.setText(title4);

	            XWPFParagraph para2 = document.createParagraph();        
	            String dataPara2 = "Ông(Bà):"+"\n\n"+order.getEmployee().getLastName()+"\n"+order.getEmployee().getFirstName();
	            XWPFRun para2Run = para2.createRun();
	            para2Run.setText(dataPara2);
	            
	            XWPFParagraph para3 = document.createParagraph();       
	            String dataPara3 = "Năm sinh:"+"\n\n" + order.getEmployee().getDateOfBirth();
	            XWPFRun para3Run = para3.createRun();
	            para3Run.setText(dataPara3);
	            
	            XWPFParagraph para4 = document.createParagraph();       
	            String dataPara4 = "Điện thoại:"+"\n\n" + order.getEmployee().getPhoneNumber();
	            XWPFRun para4Run = para4.createRun();
	            para4Run.setText(dataPara4);
	            
	            XWPFParagraph para8 = document.createParagraph();       
	            String dataPara8 = "Địa chỉ:"+"\n\n" + order.getEmployee().getAddress();
	            XWPFRun para8Run = para8.createRun();
	            para8Run.setText(dataPara8);
	            
	            XWPFParagraph titleGraph5 = document.createParagraph();
	            titleGraph5.setAlignment(ParagraphAlignment.LEFT);
	            String title5 = "BÊN MUA";           
	            XWPFRun titleRun5 = titleGraph5.createRun();
	            titleRun5.setBold(true);
	            titleRun5.setText(title5);
	            
	            XWPFParagraph para5 = document.createParagraph();        
	            String dataPara5 = "Ông(Bà):"+"\n\n"+order.getCustomer().getLastName()+"\n"+order.getCustomer().getFirstName();
	            XWPFRun para5Run = para5.createRun();
	            para5Run.setText(dataPara5);
	            
	            XWPFParagraph para6 = document.createParagraph();       
	            String dataPara6 = "Năm sinh:"+"\n\n" + order.getCustomer().getDateOfBirth();
	            XWPFRun para6Run = para6.createRun();
	            para6Run.setText(dataPara6);
	            
	            XWPFParagraph para7 = document.createParagraph();       
	            String dataPara7 = "Điện thoại:"+"\n\n" + order.getCustomer().getPhoneNumber();
	            XWPFRun para7Run = para7.createRun();
	            para7Run.setText(dataPara7);
	            
	            XWPFParagraph para9 = document.createParagraph();       
	            String dataPara9 = "Địa chỉ:"+"\n\n" + order.getCustomer().getAddress();
	            XWPFRun para9Run = para9.createRun();
	            para9Run.setText(dataPara9);
	            
	            XWPFParagraph para10 = document.createParagraph();       
	            String dataPara10 = "Chúng tôi tự nguyện cùng nhau lập và ký bản hợp đồng này để thực hiện việc mua bán xe máy/xe môtô, với những điều khoản đã được hai bên bàn bạc và thoả thuận như sau:";
	            XWPFRun para10Run = para10.createRun();
	            para10Run.setText(dataPara10);
	            
	            XWPFParagraph titleGraph6 = document.createParagraph();
	            titleGraph6.setAlignment(ParagraphAlignment.LEFT);
	            String title6 = "ĐIỀU 1: ĐẶC ĐIỂM XE MUA BÁN";           
	            XWPFRun titleRun6 = titleGraph6.createRun();
	            titleRun6.setBold(true);
	            titleRun6.setText(title6);
	            
	            List<OrderDetail> details = order.getListItems();
	            XWPFParagraph para11 = document.createParagraph();   
	            Motorbike m =dt.getMotorbike();
	            String dataPara11 = "Bên bán là chủ sở hữu của chiếc xe máy/xe môtô nhãn hiệu:"+m.getProductName()+". Loại xe:" + "\n\n"+m.getType()+". \n Màu sắc"+"\n\n"+dt.getColor() ;
	            XWPFRun para11Run = para11.createRun();
	            para11Run.setText(dataPara11);
	            
	            XWPFParagraph titleGraph7 = document.createParagraph();
	            titleGraph7.setAlignment(ParagraphAlignment.LEFT);
	            String title7 = "ĐIỀU 2: SỰ THỎA THUẬN MUA BÁN";           
	            XWPFRun titleRun7 = titleGraph7.createRun();
	            titleRun7.setBold(true);
	            titleRun7.setText(title7);
	            
//	            List<OrderDetail> details1 = order.getListItems();
//	            long y= 0;
//	            for (int i = 0; i < details1.size();i++) {
//	         	   
//	         		   y+= details1.get(i).getSubTotal();
//	         		         		  
//	            }
//	            long tt=order.getTotalVAT()+order.getTotalReg();
//	            long T = y+tt;
//	            String TT = String.format("%,d",T+ "VND");
	            XWPFParagraph para12 = document.createParagraph();       
	            String dataPara12 = "2.1. Bên bán đồng ý bán và Bên mua đồng ý mua chiếc xe nói trên như hiện trạng với giá là:"+"đồng và không thay đổi vì bất kỳ lý do gì." ;
	            XWPFRun para12Run = para12.createRun();
	            para12Run.setText(dataPara12);
	            
	            XWPFParagraph para13 = document.createParagraph();       
	            String dataPara13 = "2.2. Bên bán đã nhận đủ tiền do Bên mua trả và đã giao xe đúng như hiện trạng cho Bên mua cùng toàn bộ giấy tờ có liên quan đến chiếc xe này. Việc giao nhận không có gì vướng mắc. Việc giao tiền, giao xe được hai bên thực hiện bằng việc ký vào biên bàn bàn giao hoặc thực hiện đồng thời bằng việc ký vào hợp đồng này.";
	            XWPFRun para13Run = para13.createRun();
	            para13Run.setText(dataPara13);
	            
	            XWPFParagraph para14 = document.createParagraph();       
	            String dataPara14 = "2.3. Hai bên thoả thuận: Bên mua nộp toàn bộ các loại lệ phí, thuế liên quan đến việc mua bán ô tô.";
	            XWPFRun para14Run = para14.createRun();
	            para14Run.setText(dataPara14);
	            
	            XWPFParagraph titleGraph8 = document.createParagraph();
	            titleGraph8.setAlignment(ParagraphAlignment.LEFT);
	            String title8 = "ĐIỀU 3: CAM ĐOAN";           
	            XWPFRun titleRun8 = titleGraph8.createRun();
	            titleRun8.setBold(true);
	            titleRun8.setText(title8);
	            
	            XWPFParagraph para15 = document.createParagraph();       
	            String dataPara15 = "3.1. Bên bán cam đoan:";
	            XWPFRun para15Run = para15.createRun();
	            para15Run.setText(dataPara15);
	            
	            XWPFParagraph para16 = document.createParagraph();       
	            String dataPara16 = "Khi đem bán theo bản hợp đồng này, chiếc xe nói trên thuộc quyền sở hữu và sử dụng hợp pháp của Bên bán; chưa đem cầm cố, thế chấp hoặc dùng để đảm bảo cho bất kỳ nghĩa vụ tài sản nào. ";
	            XWPFRun para16Run = para16.createRun();
	            para16Run.setText(dataPara16);
	            
	            XWPFParagraph para17 = document.createParagraph();       
	            String dataPara17 = "3.2. Bên mua cam đoan:";
	            XWPFRun para17Run = para17.createRun();
	            para17Run.setText(dataPara17);
	            
	            XWPFParagraph para18 = document.createParagraph();       
	            String dataPara18 = "Bên mua đã tự mình xem xét kỹ, biết rõ về nguồn gốc sở hữu và hiện trạng chiếc xe nói trên của Bên bán, bằng lòng mua và không có điều gì thắc mắc.";
	            XWPFRun para18Run = para18.createRun();
	            para18Run.setText(dataPara18);
	            
	            XWPFParagraph titleGraph9 = document.createParagraph();
	            titleGraph9.setAlignment(ParagraphAlignment.LEFT);
	            String title9 = "ĐIỀU 4: ĐIỀU KHOẢN CUỐI CÙNG";           
	            XWPFRun titleRun9 = titleGraph9.createRun();
	            titleRun9.setBold(true);
	            titleRun9.setText(title9);
	            
	            XWPFParagraph para19 = document.createParagraph();       
	            String dataPara19 = "Hai bên đã tự đọc lại nguyên văn bản hợp đồng này, đều hiểu và chấp thuận toàn bộ nội dung của hợp đồng, không có điều gì vướng mắc. Hai bên cùng ký tên dưới đây để làm bằng chứng.";
	            XWPFRun para19Run = para19.createRun();
	            para19Run.setText(dataPara19);
	            
	            XWPFParagraph titleGraph10 = document.createParagraph();           
	            titleGraph10.setAlignment(ParagraphAlignment.LEFT);
	            String title10 = "BÊN BÁN"+"\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"+"BÊN MUA" ;  
	            XWPFRun titleRun10 = titleGraph10.createRun();
	            titleRun10.setBold(true);
	            titleRun10.setText(title10);
	            
	            //Bước 4: Ghi dữ liệu ra file word         
	            String s= order.getOrderID().toString() +"_"+ order.getDate().toString() + "_HopDong_" + n ;
	            String url ="D:/" + s +".docx";
		            FileOutputStream out = new FileOutputStream(url);

	            document.write(out);

	            out.close();

	            document.close();

	        } catch (IOException ex) {

	            Logger.getLogger(FormHD.class.getName()).log(Level.SEVERE, null, ex);

	        }

	    }   
	    
}
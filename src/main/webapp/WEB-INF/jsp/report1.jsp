<%@ page contentType="application/pdf" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="net.sf.jasperreports.engine.*" %>
<%@ page import="net.sf.jasperreports.engine.data.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%
	try{
		List<Map<String,String>> dataSource=new ArrayList<Map<String,String>>();;
		
		
		
			Map<String,String> m=new HashMap<String,String>();
			m.put("add_name", "sfsdfd");
			m.put("add_content", "南京夫子庙");
			m.put("add_phone_owner", "超级大魔王");
			m.put("add_phone_number","1442349273493");
			dataSource.add(m);
		JRDataSource jrDataSource=new JRBeanCollectionDataSource(dataSource);
		InputStream input=new FileInputStream(new File("L:/mygit/java/gxttcc/src/main/webapp/WEB-INF/report/v1/DemoReport1.jrxml"));
		JasperReport jasperReport=JasperCompileManager.compileReport(input);
		JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport,null,jrDataSource);
		JasperExportManager.exportReportToPdfStream(jasperPrint,response.getOutputStream());
		response.getOutputStream().flush();
		response.getOutputStream().close();

	}catch(Exception e){
		e.printStackTrace();
	}
  %>

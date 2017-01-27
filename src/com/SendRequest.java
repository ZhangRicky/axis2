package com;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service; 
import org.apache.log4j.chainsaw.Main;



/**
 * 代理请求发送
 * @author Mark
 *
 */
public class SendRequest {
	
	public String getResult(String methodName) {
		String serviceUrl ="";
		String res="";
		Service service = new Service();
		try {
			Call call;
			call = (Call) service.createCall();
			call.setTargetEndpointAddress(new java.net.URL(serviceUrl));//义乌
			call.setOperationName(methodName);//
//			call.setOperationName(new QName("http://tempuri.org/",methodName));
//            call.addParameter(new QName("http://tempuri.org/","hisurl"), org.apache.axis.encoding.XMLType.XSD_STRING,
//                          javax.xml.rpc.ParameterMode.IN);//
//            call.addParameter(new QName("http://tempuri.org/","jsonRequest"), org.apache.axis.encoding.XMLType.XSD_STRING,
//                    javax.xml.rpc.ParameterMode.IN);//
//            call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);//
//            call.setSOAPActionURI("http://tempuri.org/getHisResult");
            call.setTimeout(20000);

            res = (String)call.invoke(new Object[]{});
            System.out.println("his返回信息"+res);
          
            return res;
		}catch (RemoteException e) {
			res="RemoteException  "+e.toString()+e.getMessage();
			System.out.println(res);
		} catch (ServiceException e) {
			res="ServiceException"+e.getMessage();
			System.out.println(res);
		}catch (MalformedURLException e) {
			res="MalformedURLException"+e.getMessage();
			System.out.println(res);			
		}
		return res;
	}
	
	
	public static void main(String[] args) throws  Exception {  
//      String wsdlUrl="http://42.123.97.29:8080/zzyy_guizhou_service/services/yypt_guizhou_service?wsdl";
//       String wsdlUrl="http://localhost:8080/zzyy_guizhou_service/services/yypt_guizhou_service?wsdl";
//       org.codehaus.xfire.client.Client client = new org.codehaus.xfire.client.Client(new  URL(wsdlUrl));  
//       client.addOutHandler(new ClientAuthenticationHandler("admin","admin"));
//       String ywxml "";
//       String ywgndm = "ZJTZ";
//       Object[] obj = client.invoke("yypt_service",new Object[]{ywgndm,ywxml});
//       System.out.println(obj[0]); 
		System.err.println("123");

   }
	
}

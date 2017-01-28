package com;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axiom.om.OMElement;
import org.apache.axis.client.Call;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

public class ClientTest {
	public static void s(String args[]) throws AxisFault{
		String res="";
		org.apache.axis.client.Service service = new org.apache.axis.client.Service();
        String url ="http://localhost:8080/axis/services/hello";
		try {
			Call call;
			call = (Call) service.createCall(); 
			call.setTargetEndpointAddress(new java.net.URL(url));//义乌
			 call.setOperationName(new QName("http://com","getHello"));
//			call.setOperationName("getHello");//     
            call.addParameter(new QName("http://com","name"), org.apache.axis.encoding.XMLType.XSD_STRING,
                    javax.xml.rpc.ParameterMode.IN);//
            call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);//
            call.setTimeout(20000);

            res = (String)call.invoke(new Object[]{"<Request><Hospital_Mark>11111</Hospital_Mark><ServiceName>queryPatsCardInfo</ServiceName><Patient_Name>李淑芬</Patient_Name><Card_No/><Id_No>330702197108020812</Id_No><Phone_Number>18789378930</Phone_Number><SysType>0</SysType><Consumer>ZHIHUIYILIAOWEB</Consumer><Token>40D17306C227218CB392BE756262D025</Token></Request>"});

		 }catch (RemoteException e) {
				res="RemoteException  "+e.toString()+e.getMessage();
			}
		 catch (MalformedURLException e) {
			res="MalformedURLException"+e.getMessage();
		 }
         catch (ServiceException e) {
 			res="ServiceException"+e.getMessage();
         }
		System.out.println(res);
   }
	
	
	public static void main(String[] args) throws AxisFault {
	//  使用RPC方式调用WebService          
	    RPCServiceClient serviceClient = new RPCServiceClient();  
	    Options options = serviceClient.getOptions();  
	    //  指定调用WebService的URL  
	    EndpointReference targetEPR = new EndpointReference("http://localhost:8080/axis/services/hello");  
	    options.setTo(targetEPR);  
	    //  指定sayHelloToPerson方法的参数值  
	    Object[] opAddEntryArgs = new Object[] {"美女"};  
	    //  指定sayHelloToPerson方法返回值的数据类型的Class对象  
	    Class[] classes = new Class[] {String.class};  
	    //  指定要调用的sayHelloToPerson方法及WSDL文件的命名空间  
	    QName opAddEntry = new QName("http://com", "getHello");  
	    //  调用sayHelloToPerson方法并输出该方法的返回值  
	    System.out.println(serviceClient.invokeBlocking(opAddEntry, opAddEntryArgs, classes)[0]);  
	}
}

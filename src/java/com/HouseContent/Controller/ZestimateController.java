/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.HouseContent.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Rinjin
 */
public class ZestimateController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParserConfigurationException, SAXException {
        HashMap<String,String> zestimatemap= new HashMap();
        HashMap<String,String> valueRangemap= new HashMap();
        HashMap<String,String> addressmap= new HashMap();
        HashMap<String,String> zpidmap= new HashMap();
        HashMap<String,String> graphurl= new HashMap();
        
        
        
        String address=request.getParameter("address");
        String state=request.getParameter("state");
        String city=request.getParameter("city");
        String zipcode=request.getParameter("zipcode");
          
        String[] splitStr = address.split("\\s+");
        String firstaddress=splitStr[0];
        String secondaddress=splitStr[1];
       //  response.getWriter().print("http://www.zillow.com/webservice/GetSearchResults.htm?zws-id=X1-ZWz1f05nvrhjpn_7nbg7&address="+firstaddress +"+ " + secondaddress  +"&citystatezip="+city +"+"+state +"+"+zipcode +" ");
        URL xmlURL,xmlURLChart;
        try {
          
           // xmlURL = new URL("http://www.zillow.com/webservice/GetSearchResults.htm?zws-id=X1-ZWz1f05nvrhjpn_7nbg7&address="+firstaddress +"+ " + secondaddress  +"&citystatezip="+city +"+"+state +"+"+zipcode +" " );
            xmlURL = new URL("http://www.zillow.com/webservice/GetSearchResults.htm?zws-id=X1-ZWz1f05nvrhjpn_7nbg7&address=" +firstaddress+"+"+secondaddress +"&citystatezip="+city +"+"+ state +"+"+ zipcode );
            // response.getWriter().print(xmlURL);
            InputStream xml = xmlURL.openStream();
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(xml);
            //Prase a Zestimate
            
            NodeList estimate=doc.getElementsByTagName("zestimate");
            for (int i = 0; i < estimate.getLength(); i++) {
                Node newEstimate=estimate.item(i);
                if (newEstimate.getNodeType()==Node.ELEMENT_NODE) {
                    Element e=(Element) newEstimate;
                    NodeList childlist=e.getChildNodes();
                    for (int j = 0; j < childlist.getLength(); j++) {
                        Node newchildnode=childlist.item(j);
                        if (newchildnode.getNodeType() ==Node.ELEMENT_NODE) {
                            Element childe= (Element) newchildnode;
                             // response.getWriter().print(childe.getNodeName() + "\n");
                             // response.getWriter().print(childe.getTextContent() +"\n");
                              zestimatemap.put(childe.getNodeName(),childe.getTextContent());
                        }
                    }
                    
                  
                }
                
            }
            
                      
   //for valueRange
            NodeList valuationRangeNodeList=doc.getElementsByTagName("valuationRange");
            for (int i = 0; i < valuationRangeNodeList.getLength(); i++) {
                Node valuationRangeNode=valuationRangeNodeList.item(i);
                if (valuationRangeNode.getNodeType()==Node.ELEMENT_NODE) {
                      Element valuationRangeElement=(Element) valuationRangeNode;
                      NodeList childvalueRangeList= valuationRangeElement.getChildNodes();
                      for (int j = 0; j < childvalueRangeList.getLength(); j++) {
                            Node valuerangechildnode=childvalueRangeList.item(j);
                        if (valuerangechildnode.getNodeType() ==Node.ELEMENT_NODE) {
                            Element valuerangechild= (Element) valuerangechildnode;
                      
                              valueRangemap.put(valuerangechild.getNodeName(),valuerangechild.getTextContent());
                        }
                        
                    }
                }
                
            }
            
      //for address
          NodeList addressNodeList=doc.getElementsByTagName("address");
            for (int i = 0; i < addressNodeList.getLength(); i++) {
                Node addressNode=addressNodeList.item(i);
                if (addressNode.getNodeType()==Node.ELEMENT_NODE) {
                      Element addressElement=(Element) addressNode;
                      NodeList childvalueaddressList= addressElement.getChildNodes();
                      for (int j = 0; j < childvalueaddressList.getLength(); j++) {
                            Node childvalueaddress=childvalueaddressList.item(j);
                        if (childvalueaddress.getNodeType() ==Node.ELEMENT_NODE) {
                            Element childvalueaddresselement= (Element) childvalueaddress;
                      
                              addressmap.put(childvalueaddresselement.getNodeName(),childvalueaddresselement.getTextContent());
                        }
                        
                    }
                }
                
            }
            //get zpid
          
          NodeList responseNodeList=doc.getElementsByTagName("zpid");
            for (int i = 0; i < responseNodeList.getLength(); i++) {
                Node responseNode=responseNodeList.item(i);
                if (responseNode.getNodeType()==Node.ELEMENT_NODE) {
                      Element responseElement=(Element) responseNode;
                      zpidmap.put(responseElement.getNodeName(), responseElement.getTextContent());                   
                }
                
            }
            
            
            //getchart
            
            
            
            xmlURLChart = new URL("http://www.zillow.com/webservice/GetChart.htm?zws-id=X1-ZWz1f05nvrhjpn_7nbg7&unit-type=percent&zpid="+zpidmap.get("zpid")+"&width=300&height=150");
            // response.getWriter().print(xmlURL);
            InputStream xmlChart = xmlURLChart.openStream();
            DocumentBuilderFactory dbfChart = DocumentBuilderFactory.newInstance();
            DocumentBuilder dbChart = dbfChart.newDocumentBuilder();
            Document docChart = dbChart.parse(xmlChart);
            //Prase a Zestimate
            //Chart
            NodeList graphList=docChart.getElementsByTagName("url");
            for (int i = 0; i < graphList.getLength(); i++) {
                Node graph=graphList.item(i);
                if (graph.getNodeType()==Node.ELEMENT_NODE) {
                    Element graphelement=(Element) graph;
                          graphurl.put(graphelement.getNodeName(), graphelement.getTextContent());
                }
                
            }
            
            
            
            
            
            
            
            xml.close();
            xmlChart.close();
            
               request.setAttribute("addressMap", addressmap);
               zestimatemap.remove("valuationRange");
               zestimatemap.remove("oneWeekChange");
               request.setAttribute("ListerMap", zestimatemap);
               request.setAttribute("ValueRangeMap", valueRangemap);
               request.setAttribute("zpidMap", zpidmap);
               request.setAttribute("graphUrl", graphurl);
               RequestDispatcher view = request.getRequestDispatcher("/result.jsp");
				view.forward(request, response);
        } catch (MalformedURLException ex) {
            System.out.println(ex.toString());
        }
      
        
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParserConfigurationException ex) {
            response.getWriter().print(ex.toString());
        } catch (SAXException ex) {
            response.getWriter().print(ex.toString());
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

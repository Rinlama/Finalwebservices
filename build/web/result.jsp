<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include  file="header.jsp" %>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% HashMap map = (HashMap) request.getAttribute("ListerMap");%>

<% HashMap ValueRangemap = (HashMap) request.getAttribute("ValueRangeMap");%>
<% HashMap addressMap = (HashMap) request.getAttribute("addressMap");%>
<% HashMap requestMap = (HashMap) request.getAttribute("zpidMap");%>
<% HashMap GraphMap = (HashMap) request.getAttribute("graphUrl");%>

<body>
    <br>

    <div class="container">
        <div class="col-md-12">
            <h2>Zestimate Amount= ${ListerMap.get("amount")}</h2>
            <div class="row">
                <div class="col-md-4">
                    <img src="${graphUrl.get("url")}"/>
                </div>

                <br>

                <div class="col-md-3">
                    <p>
                        Address: ${addressMap.get("street")}<br>
                        Zip Code: ${addressMap.get("zipcode")}<br>
                        City : ${addressMap.get("city")}<br>
                        State : ${addressMap.get("state")}<br>
                        Latitude : ${addressMap.get("latitude")}<br>
                        Longitude : ${addressMap.get("longitude")}<br>
                    </p>
                </div>
                <div class="col-md-4">
                    <p>
                        Last Update: ${ListerMap.get("last-updated")}<br>
                        ValueChange: ${ListerMap.get("valueChange")}<br>
                        Low : ${ValueRangeMap.get("low")}<br>
                        High: ${ValueRangeMap.get("high")}<br>
                       
                    </p>
                </div>

            </div>

        </div>
    </div>
<body>
    <br>
    <br>
    <%@include  file="footer.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


<html>
<body>

<br/>

<a href="/emp-list.action">list all employees </a><br/><br/>


<s:form action="emp-save.action">
    <s:textfield name="firstName" label="first name"/>
    <s:textfield name="lastName" label="lastName"/>
    <s:textfield name="email" label="email"/>
    <s:submit/>
</s:form>

</body>
</html>

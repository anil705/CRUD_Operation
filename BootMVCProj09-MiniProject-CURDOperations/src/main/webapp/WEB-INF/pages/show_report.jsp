<%@ page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<c:choose>
<c:when test="${!empty empsData}">
<table border="1" bgcolor="cyan" align="center">
<tr style="color:green ;background-color:yellow">
<th>empno</th><th>emp name</th><th>emp job</th><th>emp salart</th><th>operations</th>
</tr>
<c:forEach var="emp" items="${empsData}">
<tr>
<td>${emp.empno}</td>
<td>${emp.ename}</td>
<td>${emp.job}</td>
<td>${emp.sal}</td>
<td><a href="edit?no=${emp.empno}"><img src="images/edit.jpg" width="30px" height="35px"/></a>  
&nbsp;&nbsp;&nbsp;
<a href="delete?no=${emp.empno}" onclick="return confirm('Do U wnat to Delete')"><img src="images/delete.jpg" width="30px" height="35px"/></a>


 </td>

</tr>

</c:forEach>
</table>
</c:when>
<c:otherwise>
<h1 style="color:red;text-align:center">Record not Found</h1>
</c:otherwise>

</c:choose>

<center>
<h1 style="color:green">${resultMsg}</h1>

<a href="./">home<img src="images/home.jpg" width="50px" height="100"/></a>

<a href="register">register Employee<img src="images/add.jpg" width="50px" height="100"/></a>
</center>






















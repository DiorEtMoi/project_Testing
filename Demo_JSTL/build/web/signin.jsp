<%@include file="template/header.jsp" %>
 <div id="form">
                <div id="form-title">
                    <span><a href="${pageContext.request.contextPath}/signup.jsp">SIGN UP</a></span>
                    <span><a href="#" style="color: red;">SIGN IN</a></span>
                </div>
                <div id="form-content">
                   
                    <c:if test="${msg != null}"><p>${msg}</p></c:if>
                    
                    <form  action="${path}/account/login" method="post">
                        <label>Email<span style="color: red;">*</span></label><br/>
                        <input type="text" name="txtEmail"/><br/>
                        <span class="msg-error">Email is required</span><br/>
                        <label>Password<span style="color: red;">*</span></label><br/>
                        <input type="password" name="txtPass"/><br/>
                        <span class="msg-error">Password is required</span><br/>
                        <div><a href="${pageContext.request.contextPath}/forgot">Forgot password?</a></div>
                        <input type="submit" value="SIGN IN"/><br/>
                        <input type="button" value="FACEBOOK LOGIN" style="background-color: #3b5998;"/><br/>
                        <input type="button" value="ZALO LOGIN" style="background-color: #009dff;margin-bottom: 30px;"/>
                    </form>
                </div>
            </div>

<%@include file="template/footer.jsp" %>
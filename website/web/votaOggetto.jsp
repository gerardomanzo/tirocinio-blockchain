<%@page import="bean.Criterio"%>
<%@page import="bean.Oggetto"%>
<%@page import="bean.Utente"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Vota!</title>

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" type="text/css" href="./css/bootstrap.css">

        <!-- Font Awesome CSS -->
        <link rel="stylesheet" type="text/css" href="./font-awesome/css/font-awesome.css">

        <!-- Custom CSS -->
        <link rel="stylesheet" type="text/css" href="./css/style.css">
    </head>
    <body>

        <!-- Page -->
        <div id="page-content-wrapper">

            <!-- Header -->
            <header>
                <nav class="navbar">

                    <%   Utente utente = (Utente) session.getAttribute("utente");
                        if (utente != null) {
                    %>
                    <a href="Logout" style="color: white"> <i class="fa fa-power-off fa-3x float-xs-right"></i> </a>

                    <% }  %>

                </nav>
            </header>
            <!-- /Header -->

            <!-- Main -->
            <%

                if (utente != null) {
            %>
            <section>
                <div class="container-fluid">
                    <h1 class="text-center">Benvenuto  <%=utente.getNomeUtente()%>!</h1>
                    <h2 class="text-center">Vota!</h2>
                    <div class="col-md-8 offset-md-2">
                        <div class = "form">
                            <form action="ConfermaVoti" method="POST">

                                <%
                                    List<Oggetto> oggetti = (List<Oggetto>) session.getAttribute("oggetti");
                                    List<Criterio> criteri = (List<Criterio>) session.getAttribute("criteri");

                                    int i = 0;

                                    for (Oggetto o : oggetti) {
                                %>
                                <div class="form-group row">
                                    <div class="form-box">
                                        <p class="title"><%=o.getNomeOggetto()%></p>
                                        <input type="hidden" name="idOggetto<%=i%>" value="<%=o.getIdOggetto()%>">

                                        <%
                                            int j = 0;
                                            for (Criterio c : criteri) {
                                        %>
                                        <label class="nameLabel" for="criterio<%=i%><%=j%>"><%=c.getNomeCriterio()%> (MAX = <%=c.getMaxPunteggio()%>)</label>
                                        <input type="hidden" name="idCriterio<%=i%><%=j%>" value="<%=c.getIdCriterio()%>">
                                        <input type="text" name="criterio<%=i%><%=j%>" id="criterio<%=i%><%=j%>">

                                        <%
                                                j++;
                                            }%>
                                    </div>
                                    <%
                                        i++;
                                    %>
                                </div>
                                <%  }
                                %>  


                                <input type="hidden" name="numOggetti" value="<%=oggetti.size()%>">
                                <input type="hidden" name="numCriteri" value="<%=criteri.size()%>">
                                <input type="submit" class="buttonColor" value="Vota!">
                            </form>

                            <a href="dashboardUtente.jsp">Torna alla dashboard</a>
                        </div>
                    </div>
                </div>
            </section>				
            <%
            } else {
            %>
            <section>
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-4 offset-lg-4 col-md-8 offset-md-2">
                            <div class="login-form">
                                <form method="POST" action="LoginUtente">
                                    <h1>
                                        <i class="fa fa-user-circle"></i> Login utente
                                    </h1>
                                    <input id="email" type="text" placeholder="Email" name="email"/>
                                    <input id="password" type="password" placeholder="Password" name="password"/>
                                    <button>Entra!</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <%
                }
            %>
            <!-- /Main -->

            <!-- Footer -->
            <footer> </footer>
            <!-- /Footer -->

        </div>
        <!-- /Page -->

    </div>

    <script src="./js/jquery.js"></script>
    <script src="./js/popper.js"></script>
    <script src="./js/bootstrap.js"></script>

</body>
</html>
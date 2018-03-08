<%@page import="bean.Partecipazione"%>
<%@page import="bean.Utente"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Conferma registrazione utente</title>

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

                    <%
                        Boolean login = (Boolean) session.getAttribute("loginAdmin");
                        if (login != null && login.booleanValue()) {
                    %>
                    <a href="Logout" style="color: white"> <i class="fa fa-power-off fa-3x float-xs-right"></i> </a>

                    <% }  %>

                </nav>
            </header>
            <!-- /Header -->

            <!-- Main -->
            <!-- Main -->
            <%
                if (login != null && login.booleanValue()) {
            %>
            <section>
                <div class="container-fluid">
                    <h1 class="text-center">Benvenuto amministratore!</h1>
                    <h2 class="text-center">Conferma Partecipazione</h2>
                    <div class="col-md-8 offset-md-2">
                        <table class="table table-bordered">
                            <thead class="thead-blue">
                                <tr>
                                    <th>ID Partecipazione</th>
                                    <th>ID Votazione</th>
                                    <th>ID Candidatura</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    List<Partecipazione> lista = (List<Partecipazione>) session.getAttribute("partecipazioneNonRegistrati");
                                    if (lista != null && lista.size() > 0) {
                                        for (Partecipazione u : lista) {
                                %>
                                <tr>
                                    <td><%=u.getIdPartecipazione()%></td>
                                    <td><%=u.getIdEvento().substring(25, 33)%></td>
                                    <td><%=u.getIdOggetto().substring(26, 34)%></td>
                                    <td>
                                        <form action="ConfermaPartecipazione" method="POST">
                                            <input type="hidden" name="idPartecipazione" value="<%=u.getIdPartecipazione()%>">
                                            <input type="submit" value="Conferma">
                                        </form>
                                    </td>
                                </tr>
                                <%
                                    }
                                } else {
                                %>
                                <tr>
                                    <td colspan="4">Nessuna partecipazione in attesa di conferma!</td>
                                </tr>
                                <%
                                    }
                                %>
                            </tbody>
                        </table>

                        <a href="dashboardAdmin.jsp">Torna alla dashboard</a>
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
                            <div class="form">
                                <form method="POST" action="LoginAdmin">
                                    <h1>
                                        <i class="fa fa-user-circle"></i> Login admin
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
    <script src="./js/offcanvas.js"></script>

</body>
</html>
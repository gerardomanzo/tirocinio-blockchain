<%@page import="bean.Oggetto"%>
<%@page import="bean.Utente"%>
<%@page import="bean.Evento"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Registra Oggetto ad un evento</title>

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
                    <h2 class="text-center">Registra l'oggetto a un evento</h2>
                    <div class="col-md-8 offset-md-2">
                        <form action="PartecipazioneServlet" method="POST">
                            <input type="hidden" name="action" value="InserisciPartecipazione">
                            <table class="table table-bordered">
                                <thead class="thead-blue">
                                    <tr >
                                        <th>ID</th>
                                        <th>Nome Oggetto</th>
                                        <th>Descrizione</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        List<Oggetto> lista = (List<Oggetto>) session.getAttribute("oggetti");
                                        if (lista != null && lista.size() > 0) {
                                            for (Oggetto e : lista) {
                                    %>
                                    <tr>
                                        <td><%=e.getIdOggetto()%></td>
                                        <td><%=e.getNomeOggetto()%></td>
                                        <td><%=e.getDescrizione()%></td>
                                        <td>
                                            <input type="radio" name="idOggetto" value="<%=e.getIdOggetto()%>">
                                        </td>
                                    </tr>
                                    <%
                                        }
                                    %>

                                    <%
                                    } else {
                                    %>
                                    <tr>
                                        <td colspan="5">Nessun Oggetto registrato!</td>
                                    </tr>
                                    <%
                                        }
                                    %>    
                                </tbody>

                            </table>
                            <input type="submit" value="Avanti">
                        </form>

                        <a href="dashboardUtente.jsp">Torna alla dashboard</a>
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
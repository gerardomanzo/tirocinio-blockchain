<%@page import="bean.Oggetto"%>
<%@page import="bean.Partecipazione"%>
<%@page import="bean.Voto"%>
<%@page import="bean.Utente"%>
<%@page import="bean.Evento"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Visualizza i Risultati</title>

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


                </nav>
            </header>
            <!-- /Header -->

            <!-- Main -->

            <section>
                <div class="container-fluid">
                    <h1 class="text-center">Benvenuto  <%=utente.getNomeUtente()%>!</h1>
                    <h2 class="text-center">Risultati Totali</h2>
                    <div class="col-md-8 offset-md-2">
                        <%
                            List<Voto> lista = (List<Voto>) session.getAttribute("voti");
                            List<Utente> utenti = (List<Utente>) session.getAttribute("utenti");
                            List<Partecipazione> partecipazioni = (List<Partecipazione>) session.getAttribute("partecipazioni");
                            List<Oggetto> oggetti = (List<Oggetto>) session.getAttribute("oggetti");
                            if (lista != null && lista.size() > 0) {
                        %>
                        <table class="table table-bordered">
                            <thead class="thead-blue">
                                <tr>
                                    <th>Nome Candidatura</th>
                                        <%
                                            for (Utente u : utenti) {
                                        %>
                                    <th><%=u.getNomeUtente()%></th>
                                        <%
                                            }
                                        %>
                                    <th>Totale</th>
                                </tr>
                            </thead>

                            <tbody>
                                <%
                                    for (Partecipazione p : partecipazioni) {
                                %>
                                <tr>
                                    <% for (Oggetto o: oggetti){
                                        if(p.getIdOggetto().contains(o.getIdOggetto())) {
                                    %>
                                    <td><%=o.getNomeOggetto()%></td>
                                    <% }
                                            }  
                                        %>
                                    <%
                                        int totFin = 0;

                                        for (Utente u : utenti) {
                                            int tot = 0;

                                            for (Voto v : lista) {
                                                if (v.getPartecipazione().contains(p.getIdPartecipazione()) && v.getUtente().contains(u.getIdUtente())) {
                                                    tot += v.getPunteggio();
                                                    totFin += v.getPunteggio();
                                                }
                                            }
                                    %>
                                    <td><%=tot%></td>
                                    <%
                                        }
                                    %>
                                    <td><%=totFin%></td>
                                </tr>
                                <%
                                }
                                %>
                            </tbody>
                        </table>
                        <% }else { %>
                        <p> Non ci sono voti al momento </p>
                        <% } %>
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



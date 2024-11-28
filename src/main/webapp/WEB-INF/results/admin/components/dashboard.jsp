<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/components/dashboard.css">
    <title>prova</title>
</head>
<body>
<div class="header">
    <div class="box-superiore">
        <div class="box-arancione">GESTIONE ARRIVI</div>
        <div class="box-rosso">GESTIONE SPEDIZIONI</div>
    </div>

    <div class="box-filtri">
        <form class="filtri">
            <div class="filtro">
                <label for="codice">Codice:</label>
                <input type="text" id="codice" name="codice" placeholder="Inserisci codice">
            </div>
            <div class="filtro">
                <label for="categoria">Categoria:</label>
                <select id="categoria" name="categoria">
                    <option value="">--Seleziona categoria--</option>
                    <option value="elettronica">Elettronica</option>
                    <option value="abbigliamento">Abbigliamento</option>
                    <option value="alimentari">Alimentari</option>
                    <option value="arredamento">Arredamento</option>
                </select>
            </div>
            <div class="filtro">
                <label for="nome">Nome:</label>
                <input type="text" id="nome" name="nome" placeholder="Inserisci nome">
            </div>
            <div class="filtro">
                <label for="stato">Stato:</label>
                <input type="text" id="stato" name="stato" placeholder="Inserisci stato">
            </div>
            <div class="filtro">
                <label for="data-arrivo">Data Arrivo:</label>
                <input type="date" id="data-arrivo" name="data-arrivo">
            </div>
            <div class="filtro">
                <label for="data-spedizione">Data Spedizione:</label>
                <input type="date" id="data-spedizione" name="data-spedizione">
            </div>
            <button type="submit" class="bottone-cerca">Cerca</button>
        </form>
    </div>
</div>



<div class="sezione">

    <div class="contenitore-tabella">
        <table class="tabella">
            <thead>
            <tr>
                <th>CODICE</th>
                <th>CATEGORIA</th>
                <th>NOME</th>
                <th>STATO</th>
                <th>DATA ARRIVO</th>
                <th>NOTE ARRIVO</th>
                <th>DATA SPEDIZIONE</th>
                <th>NOTE SPEDIZIONE</th>
                <th>DETTAGLI</th>
                <th>MODIFICA</th>
            </tr>
            </thead>
            <tbody>
            <!-- Riga di esempio -->
            <tr>
                <td>12345</td>
                <td>Elettronica</td>
                <td>Smartphone</td>
                <td>Disponibile</td>
                <td>2024-11-01</td>
                <td>Consegna standard</td>
                <td>2024-11-15</td>
                <td>Spedito con corriere</td>
                <td><button>Dettagli</button></td>
                <td><button>Modifica</button></td>
            </tr>
            <tr>
                <td>67890</td>
                <td>Abbigliamento</td>
                <td>Felpa</td>
                <td>Esaurito</td>
                <td>2024-10-20</td>
                <td>Consegna urgente</td>
                <td>2024-11-18</td>
                <td>Spedito con tracking</td>
                <td><button>Dettagli</button></td>
                <td><button>Modifica</button></td>
            </tr>
            <tr>
                <td>67890</td>
                <td>Abbigliamento</td>
                <td>Felpa</td>
                <td>Esaurito</td>
                <td>2024-10-20</td>
                <td>Consegna urgente</td>
                <td>2024-11-18</td>
                <td>Spedito con tracking</td>
                <td><button>Dettagli</button></td>
                <td><button>Modifica</button></td>
            </tr>
            <tr>
                <td>67890</td>
                <td>Abbigliamento</td>
                <td>Felpa</td>
                <td>Esaurito</td>
                <td>2024-10-20</td>
                <td>Consegna urgente</td>
                <td>2024-11-18</td>
                <td>Spedito con tracking</td>
                <td><button>Dettagli</button></td>
                <td><button>Modifica</button></td>
            </tr>
            <tr>
                <td>67890</td>
                <td>Abbigliamento</td>
                <td>Felpa</td>
                <td>Esaurito</td>
                <td>2024-10-20</td>
                <td>Consegna urgente</td>
                <td>2024-11-18</td>
                <td>Spedito con tracking</td>
                <td><button>Dettagli</button></td>
                <td><button>Modifica</button></td>
            </tr>
            <tr>
                <td>67890</td>
                <td>Abbigliamento</td>
                <td>Felpa</td>
                <td>Esaurito</td>
                <td>2024-10-20</td>
                <td>Consegna urgente</td>
                <td>2024-11-18</td>
                <td>Spedito con tracking</td>
                <td><button>Dettagli</button></td>
                <td><button>Modifica</button></td>
            </tr>
            <tr>
                <td>67890</td>
                <td>Abbigliamento</td>
                <td>Felpa</td>
                <td>Esaurito</td>
                <td>2024-10-20</td>
                <td>Consegna urgente</td>
                <td>2024-11-18</td>
                <td>Spedito con tracking</td>
                <td><button>Dettagli</button></td>
                <td><button>Modifica</button></td>
            </tr>
            <tr>
                <td>67890</td>
                <td>Abbigliamento</td>
                <td>Felpa</td>
                <td>Esaurito</td>
                <td>2024-10-20</td>
                <td>Consegna urgente</td>
                <td>2024-11-18</td>
                <td>Spedito con tracking</td>
                <td><button>Dettagli</button></td>
                <td><button>Modifica</button></td>
            </tr>
            <tr>
                <td>67890</td>
                <td>Abbigliamento</td>
                <td>Felpa</td>
                <td>Esaurito</td>
                <td>2024-10-20</td>
                <td>Consegna urgente</td>
                <td>2024-11-18</td>
                <td>Spedito con tracking</td>
                <td><button>Dettagli</button></td>
                <td><button>Modifica</button></td>
            </tr>
            <tr>
                <td>67890</td>
                <td>Abbigliamento</td>
                <td>Felpa</td>
                <td>Esaurito</td>
                <td>2024-10-20</td>
                <td>Consegna urgente</td>
                <td>2024-11-18</td>
                <td>Spedito con tracking</td>
                <td><button>Dettagli</button></td>
                <td><button>Modifica</button></td>
            </tr>
            <tr>
                <td>67890</td>
                <td>Abbigliamento</td>
                <td>Felpa</td>
                <td>Esaurito</td>
                <td>2024-10-20</td>
                <td>Consegna urgente</td>
                <td>2024-11-18</td>
                <td>Spedito con tracking</td>
                <td><button>Dettagli</button></td>
                <td><button>Modifica</button></td>
            </tr>
            <tr>
                <td>67890</td>
                <td>Abbigliamento</td>
                <td>Felpa</td>
                <td>Esaurito</td>
                <td>2024-10-20</td>
                <td>Consegna urgente</td>
                <td>2024-11-18</td>
                <td>Spedito con tracking</td>
                <td><button>Dettagli</button></td>
                <td><button>Modifica</button></td>
            </tr>
            <tr>
                <td>67890</td>
                <td>Abbigliamento</td>
                <td>Felpa</td>
                <td>Esaurito</td>
                <td>2024-10-20</td>
                <td>Consegna urgente</td>
                <td>2024-11-18</td>
                <td>Spedito con tracking</td>
                <td><button>Dettagli</button></td>
                <td><button>Modifica</button></td>
            </tr>
            <tr>
                <td>67890</td>
                <td>Abbigliamento</td>
                <td>Felpa</td>
                <td>Esaurito</td>
                <td>2024-10-20</td>
                <td>Consegna urgente</td>
                <td>2024-11-18</td>
                <td>Spedito con tracking</td>
                <td><button>Dettagli</button></td>
                <td><button>Modifica</button></td>
            </tr>
            <tr>
                <td>67890</td>
                <td>Abbigliamento</td>
                <td>Felpa</td>
                <td>Esaurito</td>
                <td>2024-10-20</td>
                <td>Consegna urgente</td>
                <td>2024-11-18</td>
                <td>Spedito con tracking</td>
                <td><button>Dettagli</button></td>
                <td><button>Modifica</button></td>
            </tr>
            <tr>
                <td>67890</td>
                <td>Abbigliamento</td>
                <td>Felpa</td>
                <td>Esaurito</td>
                <td>2024-10-20</td>
                <td>Consegna urgente</td>
                <td>2024-11-18</td>
                <td>Spedito con tracking</td>
                <td><button>Dettagli</button></td>
                <td><button>Modifica</button></td>
            </tr>
            <tr>
                <td>67890</td>
                <td>Abbigliamento</td>
                <td>Felpa</td>
                <td>Esaurito</td>
                <td>2024-10-20</td>
                <td>Consegna urgente</td>
                <td>2024-11-18</td>
                <td>Spedito con tracking</td>
                <td><button>Dettagli</button></td>
                <td><button>Modifica</button></td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>

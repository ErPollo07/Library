import data.JsonReader;
import events.Loan;
import events.Sell;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

class Initialize {
    private ArrayList<User> users;
    private LoginManager loginManager;

    public Initialize(String path) {
        users = new ArrayList<>();
        users = loadUsersFromFile(path);
    }

    /**
     * Loads a list of users from a specified JSON file, populating each user
     * with their respective loan (loans) and sale (sells) data.
     * <p>
     * The JSON file must have the following structure:
     * <pre>
     * [
     *   {
     *     "id": "string",
     *     "password": "string",
     *     "name": "string",
     *     "surname": "string",
     *     "phoneNumber": integer,
     *     "loans": [
     *       {
     *         "isbn": "string",
     *         "loanDate": "string (format: dd-MM-yyyy)",
     *         "expirationDate": "string (format: dd-MM-yyyy)"
     *       },
     *       ...
     *     ],
     *     "sells": [
     *       {
     *         "isbnSoldBook": "string",
     *         "idUser": "string",
     *         "price": integer,
     *         "sellDate": "string (format: dd-MM-yyyy)"
     *       },
     *       ...
     *     ]
     *   },
     *   ...
     * ]
     * </pre>
     *
     * @param path the path of the JSON file from which to load user data.
     * @return a list of `User` objects loaded from the JSON file.
     */

    private ArrayList<User> loadUsersFromFile(String path) {
        ArrayList<User> users = new ArrayList<>();

        JSONArray jsonArray = JsonReader.readArray(path);

        // Cycle through the json array of jsonObjects
        for (Object obj : jsonArray) {
            JSONObject jsonUser = (JSONObject) obj;

            // Get the loans from the json Obj of the user
            ArrayList<Loan> loans = new ArrayList<>();
            JSONArray jsonLoans = (JSONArray) jsonUser.get("loans");

            for (Object objLoans : jsonLoans) {
                JSONObject jsonLoan = (JSONObject) objLoans;

                loans.add(new Loan(
                        (String) jsonLoan.get("isbn"),
                        LocalDate.parse((String) jsonLoan.get("loanDate"), DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                        LocalDate.parse((String) jsonLoan.get("expirationDate"), DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                ));
            }

            // Get the sells from the json Obj of the user
            ArrayList<Sell> sells = new ArrayList<>();
            JSONArray jsonSells = (JSONArray) jsonUser.get("sells");

            for (Object objSell : jsonSells) {
                JSONObject jsonSell = (JSONObject) objSell;

                sells.add(new Sell(
                        (String) jsonSell.get("isbnSoldBook"),
                        (String) jsonSell.get("idUser"),
                        (int) jsonSell.get("price"),
                        LocalDate.parse((String) jsonSell.get("sellDate"), DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                ));
            }

            users.add(new User(
                    (String) jsonUser.get("id"), (String) jsonUser.get("password"),
                    (String) jsonUser.get("name"), (String) jsonUser.get("surname"),
                    (int) jsonUser.get("phoneNumber"),
                    loans, sells
            ));
        }

        return users;
    }

    public ArrayList<User> getUsers() {
        return users;
    }
}
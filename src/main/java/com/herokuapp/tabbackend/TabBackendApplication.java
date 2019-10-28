package com.herokuapp.tabbackend;

import com.squareup.connect.ApiClient;
import com.squareup.connect.ApiException;
import com.squareup.connect.api.PaymentsApi;
import com.squareup.connect.auth.OAuth;
import com.squareup.connect.models.*;

import java.io.IOException;
import java.util.Currency;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
public class TabBackendApplication extends HttpServlet {

	public static ResourceBundle resourceBundle = ResourceBundle.getBundle("config");
	public static final String APIURL = resourceBundle.getString("yodlee.APIURL");
	public static final String APIKEY = resourceBundle.getString("yodlee.apiKey");
	public static final String NODEURL = resourceBundle.getString("yodlee.nodeUrl");
	// TODO: put keys in .env heroku file
	private static final String SQUARE_ACCESS_TOKEN_ENV_VAR = "";
	private static final String SQUARE_APP_ID_ENV_VAR  = "";
	private static final String SQUARE_LOCATION_ID_ENV_VAR = "";
	private static final String SQUARE_ENV_ENV_VAR = "";

	private final ApiClient squareClient;
	private final String squareLocationId;
	private final String squareAppId;
	private final String squareEnvironment;

	public TabBackendApplication() throws ApiException{
		squareEnvironment = mustLoadEnvironmentVariable(SQUARE_ENV_ENV_VAR);
		squareAppId = mustLoadEnvironmentVariable(SQUARE_APP_ID_ENV_VAR);
		squareLocationId = mustLoadEnvironmentVariable(SQUARE_LOCATION_ID_ENV_VAR);

		squareClient = newApiClient();
		OAuth oauth2 = (OAuth) squareClient.getAuthentication("oauth2");
		oauth2.setAccessToken(mustLoadEnvironmentVariable(SQUARE_ACCESS_TOKEN_ENV_VAR));
		squareClient.setBasePath(squareEnvironment.equals("sandbox") ? "https://connect.squareupsandbox.com" : "https://connect.squareup.com");
	}


	public static void main(String[] args) {
		SpringApplication.run(TabBackendApplication.class, args);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bearer = null;
		String action = (String) request.getParameter("action");
		if (action !=null){
			bearer = (String) requst.getSession().getAttribute("Bearer");
			if (bearer != null && bearer.length() > 0){
				if(action.equals("getAccounts")){
					String accountsJson = getUserAccounts(bearer);
					//send response
				}
			}
		}
	}
	private String mustLoadEnvironmentVariable(String name){
		String value = System.getenv(name);
		if (value == null || value.length() == 0){
			throw new IllegalStateException(
					String.format("The %s environment variable must be set", name));
			)
			return value;
		}
	}
	@RequestMapping("/")
	String index(Map<String, Object> model) throws ApiException{
		model.put("paymentFormUrl", squareEnvironment.equals("sandbox") ?
				"https://js.squareupsandbox.com/v2/paymentform" : "https://js.squareup.com/v2/paymentform");
		model.put("locationId", squareLocationId);
		model.put("appId", squareAppId);
		return "index";
	}
	//TODO: make connect to external nonce
	@PostMapping("/charge")
	String charge(@ModelAttribute NonceForm form, Map<String, Object> model) throws ApiException{
		CreatePaymentRequest createPaymentRequest = new CreatePaymentRequest()
				.idempotencyKey(UUID.randomUUID().toString())
				.amountMoney(new Money().amount(1_00L).currency("USD"))
				.sourceId(form.getNonce())
				.note("Initial transaction");
		PaymentsApi paymentsApi = new PaymentsApi(squareClient);
		CreatePaymentResponse response = paymentsApi.createPayment(createPaymentRequest);
		model.put("payment", response.getPayment());
		return "charge";
	}
}

package api.endpoints;
/*
 * Swagger URI ---> petstore.swagger.io
 * Create User(post) : https://petstore.swagger.io/v2/user
 * Get User (Get): https://petstore.swagger.io/v2/user/{username}
 * Update User (Put) : https://petstore.swagger.io/v2/user/{username}
 * Delete User (Delete) : https://petstore.swagger.io/v2/user/{username}
 * 
 */
public class Routes {
	
	public static String base_url = "https://petstore.swagger.io/v2";
	
	//User 
	public static String post_url = base_url+"/user";
	public static String get_url = base_url+"/user/{username}";
	public static String update_url = base_url+"/user/{username}";
	public static String delete_url = base_url+"/user/{username}";

	
	//pet
	public static String post_url1 = base_url+"/pet";
	public static String get_url1 = base_url+"/pet/{id}";
	public static String update_url1 = base_url+"/pet";
	public static String delete_url1 = base_url+"/pet/{id}";
	
	//Store
	public static String post_url2 = base_url+"/store/order";
	public static String get_url2 = base_url+"/store/order/{id}";
	public static String delete_url2 = base_url+"/store/order/{id}";

}

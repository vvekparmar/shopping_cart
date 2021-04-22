/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ankur
 */
public class showcart extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/artcart","root","");
                    Statement st = con.createStatement();
                    //out.println("<h>connection done</h>");
                    ResultSet rs;
                    String email;    
                    //name =request.getParameter("name");
                    Cookie ck[]=request.getCookies();  
                    HttpSession session = request.getSession(false);
                    if(session!=null){  
                         email=(String)session.getAttribute("email");
                         
                        String name=(String)session.getAttribute("name");
                        
                        if(!email.equals("")||email!=null){ 
                            int total=0;
                            
                            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM cart WHERE email=?");
                            pstmt.setString(1, email);
                            ResultSet rs1 = pstmt.executeQuery();
            
                            out.println("<!doctype html>\n" +
"<html lang=\"zxx\">\n" +
"\n" +
"<head>\n" +
"  <!-- Required meta tags -->\n" +
"  <meta charset=\"utf-8\">\n" +
"  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\n" +
"  <title>ArtCart | Home :: ArtStore</title>\n" +
"  <!-- Template CSS -->\n" +
"  <link rel=\"stylesheet\" href=\"assets/css/style-starter.css\">\n" +
"  <!-- Template CSS -->\n" +
"  <link href=\"//fonts.googleapis.com/css?family=Oswald:300,400,500,600&display=swap\" rel=\"stylesheet\">\n" +
"  <link href=\"//fonts.googleapis.com/css?family=Lato:300,300i,400,400i,700,900&display=swap\" rel=\"stylesheet\">\n" +
"  <!-- Template CSS -->\n" +
"\n" +
"</head>\n" +
"<style>\n" +
"		#table {\n" +
"			font-family: \"Roboto\", sans-serif;\n" +
"			padding:10px;\n" +
"			width: 75%;\n" +
"			margin: auto;\n" +
"			margin-top: 100px;\n" +
"			border-collapse: collapse;\n" +
"		}\n" +
"		td{\n" +
"			padding: 15px;\n" +
"		}\n" +
"		#table_tag {\n" +
"			font-family: \"Roboto\", sans-serif;\n" +
"		font-weight: bold;\n" +
"		text-align: center;\n" +
"		color: rgba(40, 58, 90, 0.9);\n" +
"		}\n" +
"\n" +
"		\n" +
"		\n" +
"	</style>\n" +
"<body>\n" +
"<!--w3l-banner-slider-main-->\n" +
"<section class=\"w3l-banner-slider-main\">\n" +
"	<div class=\"top-header-content\">\n" +
"		<header class=\"tophny-header\">\n" +
"			<div class=\"container-fluid\">\n" +
"				<div class=\"top-right-strip row\">\n" +
"					<!--/left-->\n" +
"					<div class=\"top-hny-left-content col-lg-6 pl-lg-0\">\n" +
"						<h6>Upto 30% off on All Arts , <a href=\"#\" target=\"_blank\"> Click here for <span\n" +
"									class=\"fa fa-hand-o-right hand-icon\" aria-hidden=\"true\"></span> <span\n" +
"									class=\"hignlaite\">More details</span></a></h6>\n" +
"					</div>\n" +
"					<!--//left-->\n" +
"					<!--/right-->\n" +
"					<ul class=\"top-hnt-right-content col-lg-6\">\n" +
"\n" +
"						<li class=\"button-log usernhy\">\n" +
"							<a class=\"btn-open\" href=\"#\">\n" +
"								<span class=\"fa fa-user\" aria-hidden=\"true\"></span>\n" +
"							</a>\n" +
"						</li>\n" +
"						<li class=\"transmitvcart galssescart2 cart cart box_1\">\n" +
"							<form action=\"showcart\" method=\"post\" class=\"last\">\n" +
"								<input type=\"hidden\" name=\"cmd\" value=\"_cart\">\n" +
"								<input type=\"hidden\" name=\"display\" value=\"1\">\n" +
"								<button class=\"top_transmitv_cart\" type=\"submit\" name=\"submit\" value=\"\">\n" +
"									My Cart\n" +
"									<span class=\"fa fa-shopping-cart\"></span>\n" +
"								</button>\n" +
"							</form>\n" +
"						</li>\n" +
"					</ul>\n" +
"					<!--//right-->\n" +
"					<div class=\"overlay-login text-left\">\n" +
"						<button type=\"button\" class=\"overlay-close1\">\n" +
"							<i class=\"fa fa-times\" aria-hidden=\"true\"></i>\n" +
"						</button>\n" +
"						<div class=\"wrap\">\n" +
"							<h5 class=\"text-center mb-4\">Logout</h5>\n" +
"							<div class=\"login-bghny p-md-5 p-4 mx-auto mw-100\">\n" +
"								<!--/logout-form-->\n" +
"								<form action=\"logout\" method=\"post\">\n" +
"									\n" +
"									\n" +
"									<button type=\"submit\" class=\"submit-login btn mb-4\">Log Out</button>\n" +
"\n" +
"								</form>\n" +
"\n" +
"									\n" +
"								<!--//logout-form-->\n" +
"							</div>\n" +
"							<!---->\n" +
"						</div>\n" +
"\n" +
"\n" +
"						\n" +
"					</div>\n" +
"				</div>\n" +
"			</div>\n" +
"			<!--/nav-->\n" +
"			<nav class=\"navbar navbar-expand-lg navbar-light\">\n" +
"				<div class=\"container-fluid serarc-fluid\">\n" +
"					<a class=\"navbar-brand\" href=\"index.html\">\n" +
"						<img src=\"assets/images/Logo.png\"  width=\"50\" height=\"50\" style=\"float:left\">Art<span class=\"lohny\">C</span>art</a>\n" +
"							<!--<a class=\"navbar-brand\" href=\"#index.html\">\n" +
"								<img src=\"i\" alt=\"Your logo\" title=\"Your logo\" style=\"height:35px;\" />\n" +
"							</a>-->\n" +
"					<!--/search-right-->\n" +
"					<div class=\"search-right\">\n" +
"\n" +
"						<a href=\"#search\" title=\"search\"><span class=\"fa fa-search mr-2\" aria-hidden=\"true\"></span>\n" +
"							<span class=\"search-text\">Search here</span></a>\n" +
"						<!-- search popup -->\n" +
"						<div id=\"search\" class=\"pop-overlay\">\n" +
"							<div class=\"popup\">\n" +
"\n" +
"								<form action=\"#\" method=\"post\" class=\"search-box\">\n" +
"									<input type=\"search\" placeholder=\"Keyword\" name=\"search\" required=\"required\"\n" +
"										autofocus=\"\">\n" +
"									<button type=\"submit\" class=\"btn\">Search</button>\n" +
"								</form>\n" +
"\n" +
"							</div>\n" +
"							<a class=\"close\" href=\"#\">×</a>\n" +
"						</div>\n" +
"						<!-- /search popup -->\n" +
"					</div>\n" +
"					<!--//search-right-->\n" +
"					<button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\"\n" +
"						data-target=\"#navbarSupportedContent\" aria-controls=\"navbarSupportedContent\"\n" +
"						aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n" +
"						<span class=\"navbar-toggler-icon fa fa-bars\"> </span>\n" +
"					</button>\n" +
"					<div class=\"collapse navbar-collapse\" id=\"navbarSupportedContent\">\n" +
"						<ul class=\"navbar-nav ml-auto\">\n" +
"							<li class=\"nav-item active\">\n" +
"								<a class=\"nav-link\" href=\"home.html\">Home</a>\n" +
"\n" +
"							</li>\n" +
"							 <li class=\"nav-item\">\n" +
"								<a class=\"nav-link\" href=\"show_order\">Past Orders</a>\n" +
"							</li>\n" +
"							<li class=\"nav-item\">\n" +
"								<a class=\"nav-link\" href=\"artcartshop\">Buy</a>\n" +
"							</li>\n" +
"							<li class=\"nav-item\">\n" +
"								<a class=\"nav-link\" href=\"#\">About</a>\n" +
"							</li>\n" +
"							<li class=\"nav-item\">\n" +
"								<a class=\"nav-link\" href=\"#\">What We Offer</a>\n" +
"							  </li>\n" +
"\n" +
"							 \n" +
"\n" +
"\n" +
"							<li class=\"nav-item\">\n" +
"								<a class=\"nav-link\" href=\"#\">Contact</a>\n" +
"							</li>\n" +
"						</ul>\n" +
"\n" +
"					</div>\n" +
"				</div>\n" +
"			</nav>\n" +
"			<!--//nav-->\n" +
"		</header>\n" +
"\n" +
"\n" +
"		<div class=\"bannerhny-content\">\n" +
"\n" +
"			<!--/banner-slider-->\n" +
"			<div class=\"content-baner-inf\">\n" +
"				<div id=\"carouselExampleIndicators\" class=\"carousel slide\" data-ride=\"carousel\">\n" +
"					<ol class=\"carousel-indicators\">\n" +
"						<li data-target=\"#carouselExampleIndicators\" data-slide-to=\"0\" class=\"active\"></li>\n" +
"						<li data-target=\"#carouselExampleIndicators\" data-slide-to=\"1\"></li>\n" +
"						<li data-target=\"#carouselExampleIndicators\" data-slide-to=\"2\"></li>\n" +
"						<li data-target=\"#carouselExampleIndicators\" data-slide-to=\"3\"></li>\n" +
"					</ol>\n" +
"					<div class=\"carousel-inner\">\n" +
"						<div class=\"carousel-item active\">\n" +
"							<div class=\"container\">\n" +
"								<div class=\"carousel-caption\">\n" +
"									<h3>Artwork Product\n" +
"										<br>50% Off</h3>\n" +
"									<a href=\"#\" class=\"shop-button btn\">\n" +
"										Shop Now\n" +
"									</a>\n" +
"\n" +
"								</div>\n" +
"							</div>\n" +
"						</div>\n" +
"						<div class=\"carousel-item item2\">\n" +
"							<div class=\"container\">\n" +
"								<div class=\"carousel-caption\">\n" +
"									<h3>Now Artwork\n" +
"										<br>60% Off</h3>\n" +
"									<a href=\"#\" class=\"shop-button btn\">\n" +
"										Shop Now\n" +
"									</a>\n" +
"\n" +
"								</div>\n" +
"							</div>\n" +
"						</div>\n" +
"						<div class=\"carousel-item item3\">\n" +
"							<div class=\"container\">\n" +
"								<div class=\"carousel-caption\">\n" +
"									<h3>Artwork Product\n" +
"										<br>50% Off</h3>\n" +
"									<a href=\"#\" class=\"shop-button btn\">\n" +
"										Shop Now\n" +
"									</a>\n" +
"\n" +
"								</div>\n" +
"							</div>\n" +
"						</div>\n" +
"						<div class=\"carousel-item item4\">\n" +
"							<div class=\"container\">\n" +
"								<div class=\"carousel-caption\">\n" +
"									<h3>Now Artwork\n" +
"										<br>60% Off</h3>\n" +
"									<a href=\"#\" class=\"shop-button btn\">\n" +
"										Shop Now\n" +
"									</a>\n" +
"								</div>\n" +
"							</div>\n" +
"						</div>\n" +
"					</div>\n" +
"					<a class=\"carousel-control-prev\" href=\"#carouselExampleIndicators\" role=\"button\" data-slide=\"prev\">\n" +
"						<span class=\"carousel-control-prev-icon\" aria-hidden=\"true\"></span>\n" +
"						<span class=\"sr-only\">Previous</span>\n" +
"					</a>\n" +
"					<a class=\"carousel-control-next\" href=\"#carouselExampleIndicators\" role=\"button\" data-slide=\"next\">\n" +
"						<span class=\"carousel-control-next-icon\" aria-hidden=\"true\"></span>\n" +
"						<span class=\"sr-only\">Next</span>\n" +
"					</a>\n" +
"				</div>\n" +
"			</div>\n" +
"			<!--//banner-slider-->\n" +
"			<!--//banner-slider-->\n" +
"			<div class=\"right-banner\">\n" +
"				<div class=\"right-1\">\n" +
"					<h4>\n" +
"						Fine Artwork\n" +
"						\n" +
"						<br>50% Off</h4>\n" +
"				</div>\n" +
"			</div>\n" +
"\n" +
"		</div>\n" +
"\n" +
"</section>\n" +
"\n" +
"");
                            out.println("<h1 id=\"table_tag\" style=\"margin-top: 100px\" >Your Cart "+name+"</h1>\n" +
"\n" +
"		<div id=\"div_prac_1\" >\n" +
"\n" +
"			<table style=\"margin-bottom: 100px\" id=\"table\" border=\"0\" ><tr>\n" +
"					<td style=\"text-align:center\">Item Name</td>\n" +
"					<td style=\"text-align:center\">Price</td>\n" +
"					\n" +
"				</tr>");
                            float ttl = 0;
                            while(rs1.next()){
                                String it = rs1.getString("item");
                                float p = rs1.getFloat("price");
                                ttl +=p;
                                String pr = String.valueOf(p);
                                out.println("<tr><form action = \"removeitem\">\n" +
"                  <input type=\"hidden\" name=\"item\" value=\"" + it + "\">\n" +
"                  <td style=\"text-align:center\">"+it+ "</td>\n" +
"                  <td style=\"text-align:center\"> "+p+"$</td>\n" +
"                  <td style=\"text-align:center\"><button style=\"border-radius: 8px;background-color: rgba(40, 58, 90, 0.9);border: none;color: white;padding: 8px;text-align: center;text-decoration: none;display: inline-block;font-size: 16px;margin: 4px 2px;cursor: pointer;\">REMOVE</button></td>\n" +
"                </form></tr>");
                     
                            }
                            String sttl = String.valueOf(ttl);
                            out.println("<tr>\n" +
"                <form action = \"store_order\">\n" +
"                  \n" +
"                  <td style=\"text-align:center;\"> Total</td>\n" +
"                  <td style=\"text-align:center;\">"+ sttl+ "$ </td>\n" +
"                  <td style=\"text-align:center;\"><button style=\"border-radius: 8px;background-color: rgba(40, 58, 90, 0.9);border: none;color: white;padding: 8px;text-align: center;text-decoration: none;display: inline-block;font-size: 16px;margin: 4px 2px;cursor: pointer;\">CHECK OUT</button></td>\n" +
"                </form>\n" +
"              </tr></table>");
                            
                           
                            out.println("<section class=\"w3l-subscription-6\">\n" +
"	<!--/customers -->\n" +
"	<div class=\"subscription-infhny\">\n" +
"		<div class=\"container-fluid\">\n" +
"\n" +
"			<div class=\"subscription-grids row\">\n" +
"\n" +
"				<div class=\"subscription-right form-right-inf col-lg-6 p-md-5 p-4\">\n" +
"					<div class=\"p-lg-5 py-md-0 py-3\">\n" +
"						<h3 class=\"hny-title\">Join us for FREE to get instant <span>email updates!</span></h3>\n" +
"							<p>Subscribe and get notified at first on the latest update and offers!</p>\n" +
"\n" +
"						<form action=\"#\" method=\"post\" class=\"signin-form mt-lg-5 mt-4\">\n" +
"							<div class=\"forms-gds\">\n" +
"								<div class=\"form-input\">\n" +
"									<input type=\"email\" name=\"\" placeholder=\"Your email here\" required=\"\">\n" +
"								</div>\n" +
"								<div class=\"form-input\"><button class=\"btn\">Join</button></div>\n" +
"							</div>\n" +
"						</form>\n" +
"					</div>\n" +
"				</div>\n" +
"				<div class=\"subscription-left forms-25-info col-lg-6 \">\n" +
"\n" +
"				</div>\n" +
"			</div>\n" +
"\n" +
"			<!--//customers -->\n" +
"		</div>\n" +
"</section>\n" +
"<!-- //customers-6-->\n" +
"\n" +
"\n" +
"  <section class=\"w3l-footer-22\">\n" +
"      <!-- footer-22 -->\n" +
"      <div class=\"footer-hny py-5\">\n" +
"          <div class=\"container py-lg-5\">\n" +
"              <div class=\"text-txt row\">\n" +
"                  <div class=\"left-side col-lg-4\">\n" +
"                      <h3><a class=\"logo-footer\" href=\"index.html\">\n" +
"                            <img src=\"assets/images/Logo.png\"  width=\"50\" height=\"50\" style=\"float:left\">Art<span class=\"lohny\">C</span>art</a></h3>\n" +
"                      <!-- if logo is image enable this   \n" +
"                                    <a class=\"navbar-brand\" href=\"#index.html\">\n" +
"                                        <img src=\"image-path\" alt=\"Your logo\" title=\"Your logo\" style=\"height:35px;\" />\n" +
"                                    </a> -->\n" +
"                      <p>The online platform for budding artists to bring out their work out to the world . \n" +
"                          Buy and Artworks from independent artists. Find the perfect original paintings, \n" +
"                          digital illustrations at an affordable price range.  </p>\n" +
"                      <ul class=\"social-footerhny mt-lg-5 mt-4\">\n" +
"\n" +
"                          <li><a class=\"facebook\" href=\"#\"><span class=\"fa fa-facebook\" aria-hidden=\"true\"></span></a>\n" +
"                          </li>\n" +
"                          <li><a class=\"twitter\" href=\"#\"><span class=\"fa fa-twitter\" aria-hidden=\"true\"></span></a>\n" +
"                          </li>\n" +
"                          <li><a class=\"google\" href=\"#\"><span class=\"fa fa-google-plus\" aria-hidden=\"true\"></span></a>\n" +
"                          </li>\n" +
"                          <li><a class=\"instagram\" href=\"#\"><span class=\"fa fa-instagram\" aria-hidden=\"true\"></span></a>\n" +
"                          </li>\n" +
"                      </ul>\n" +
"                  </div>\n" +
"\n" +
"                  <div class=\"right-side col-lg-8 pl-lg-5\">\n" +
"                      <h4>World Art Day Special Offer\n" +
"                        All Concept Arts are Flat 50% Discount</h4>\n" +
"                      <div class=\"sub-columns\">\n" +
"                          <div class=\"sub-one-left\">\n" +
"                              <h6>Useful Links</h6>\n" +
"                              <div class=\"footer-hny-ul\">\n" +
"                                  <ul>\n" +
"                                      <li><a href=\"index.html\">Home</a></li>\n" +
"                                      <li><a href=\"about.html\">About</a></li>\n" +
"                                      <li><a href=\"#\">Blog</a></li>\n" +
"                                      <li><a href=\"contact.html\">Contact</a></li>\n" +
"                                  </ul>\n" +
"                                  <ul>\n" +
"                                      <li><a href=\"#\">Careers</a></li>\n" +
"                                      <li><a href=\"#\">Privacy Policy</a></li>\n" +
"                                      <li><a href=\"#\">Terms and Conditions</a></li>\n" +
"                                      <li><a href=\"contact.html\">Support</a></li>\n" +
"                                  </ul>\n" +
"                              </div>\n" +
"\n" +
"                          </div>\n" +
"                          <div class=\"sub-two-right\">\n" +
"                              <h6>Our Store</h6>\n" +
"                              <p class=\"mb-5\">BVM Anand, India</p>\n" +
"\n" +
"                              <h6>We accept:</h6>\n" +
"                              <ul>\n" +
"                                  <li><a class=\"pay-method\" href=\"#\"><span class=\"fa fa-cc-visa\"\n" +
"                                              aria-hidden=\"true\"></span></a>\n" +
"                                  </li>\n" +
"                                  <li><a class=\"pay-method\" href=\"#\"><span class=\"fa fa-cc-mastercard\"\n" +
"                                              aria-hidden=\"true\"></span></a>\n" +
"                                  </li>\n" +
"                                  <li><a class=\"pay-method\" href=\"#\"><span class=\"fa fa-cc-paypal\"\n" +
"                                              aria-hidden=\"true\"></span></a>\n" +
"                                  </li>\n" +
"                                  <li><a class=\"pay-method\" href=\"#\"><span class=\"fa fa-cc-amex\"\n" +
"                                              aria-hidden=\"true\"></span></a>\n" +
"                                  </li>\n" +
"                              </ul>\n" +
"                          </div>\n" +
"                      </div>\n" +
"                  </div>\n" +
"              </div>\n" +
"              <div class=\"below-section row\">\n" +
"                  <div class=\"columns col-lg-6\">\n" +
"                      <ul class=\"jst-link\">\n" +
"                          <li><a href=\"#\">Privacy Policy </a> </li>\n" +
"                          <li><a href=\"#\">Term of Service</a></li>\n" +
"                          <li><a href=\"contact.html\">Customer Care</a> </li>\n" +
"                      </ul>\n" +
"                  </div>\n" +
"                  <div class=\"columns col-lg-6 text-lg-right\">\n" +
"                      <p>© 2020 ArtCart. All rights reserved. Design by Dip & VASU\n" +
"                      </p>\n" +
"                  </div>\n" +
"                  <button onclick=\"topFunction()\" id=\"movetop\" title=\"Go to top\">\n" +
"                      <span class=\"fa fa-angle-double-up\"></span>\n" +
"                  </button>\n" +
"              </div>\n" +
"          </div>\n" +
"      </div>\n" +
"      <!-- //titels-5 -->\n" +
"      <!-- move top -->\n" +
"\n" +
"      <script>\n" +
"          // When the user scrolls down 20px from the top of the document, show the button\n" +
"          window.onscroll = function () {\n" +
"              scrollFunction()\n" +
"          };\n" +
"\n" +
"          function scrollFunction() {\n" +
"              if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {\n" +
"                  document.getElementById(\"movetop\").style.display = \"block\";\n" +
"              } else {\n" +
"                  document.getElementById(\"movetop\").style.display = \"none\";\n" +
"              }\n" +
"          }\n" +
"\n" +
"          // When the user clicks on the button, scroll to the top of the document\n" +
"          function topFunction() {\n" +
"              document.body.scrollTop = 0;\n" +
"              document.documentElement.scrollTop = 0;\n" +
"          }\n" +
"      </script>\n" +
"      <!-- /move top -->\n" +
"  </section>\n" +
"\n" +
"\n" +
"  </body>\n" +
"\n" +
"  </html>\n" +
"\n" +
"<script src=\"assets/js/jquery-3.3.1.min.js\"></script>\n" +
"<script src=\"assets/js/jquery-2.1.4.min.js\"></script>\n" +
"<!--/login-->\n" +
"<script>\n" +
"		$(document).ready(function () {\n" +
"			$(\".button-log a\").click(function () {\n" +
"				$(\".overlay-login\").fadeToggle(200);\n" +
"				$(this).toggleClass('btn-open').toggleClass('btn-close');\n" +
"			});\n" +
"		});\n" +
"		$('.overlay-close1').on('click', function () {\n" +
"			$(\".overlay-login\").fadeToggle(200);\n" +
"			$(\".button-log a\").toggleClass('btn-open').toggleClass('btn-close');\n" +
"			open = false;\n" +
"		});\n" +
"  </script>\n" +
"<!--//login-->\n" +
"<script>\n" +
"// optional\n" +
"		$('#customerhnyCarousel').carousel({\n" +
"				interval: 5000\n" +
"    });\n" +
"  </script>\n" +
" <!-- cart-js -->\n" +
" <script src=\"assets/js/minicart.js\"></script>\n" +
" \n" +
" <!-- //cart-js -->\n" +
"<!--pop-up-box-->\n" +
"<script src=\"assets/js/jquery.magnific-popup.js\"></script>\n" +
"<!--//pop-up-box-->\n" +
"<script>\n" +
"  $(document).ready(function () {\n" +
"    $('.popup-with-zoom-anim').magnificPopup({\n" +
"      type: 'inline',\n" +
"      fixedContentPos: false,\n" +
"      fixedBgPos: true,\n" +
"      overflowY: 'auto',\n" +
"      closeBtnInside: true,\n" +
"      preloader: false,\n" +
"      midClick: true,\n" +
"      removalDelay: 300,\n" +
"      mainClass: 'my-mfp-zoom-in'\n" +
"    });\n" +
"\n" +
"  });\n" +
"</script>\n" +
"<!--//search-bar-->\n" +
"<!-- disable body scroll which navbar is in active -->\n" +
"\n" +
"<script>\n" +
"  $(function () {\n" +
"    $('.navbar-toggler').click(function () {\n" +
"      $('body').toggleClass('noscroll');\n" +
"    })\n" +
"  });\n" +
"</script>\n" +
"<!-- disable body scroll which navbar is in active -->\n" +
"<script src=\"assets/js/bootstrap.min.js\"></script>\n" +
"\n" +
"<form onload=\"onload_script()\">\n" +
"\n" +
"<script>\n" +
"function onload_script() {\n" +
"  document.getElementById(\"form_id\").action = zzz;\n" +
"}\n" +
"</script>\n" +
"");
                            
                            
                            
                            
                        }
                    }
                    else{
                        request.getRequestDispatcher("index.html").include(request, response);
                        out.println("<script type=\"text/javascript\">");
                        out.println("alert('Login First');");
                        out.println("location='index.html';");
                        out.println("</script>");
                    }
        }
        catch(Exception e){
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

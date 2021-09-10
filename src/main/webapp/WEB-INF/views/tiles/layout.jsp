<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Date"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>   

<!-- 모든 header footer 적용 --> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>todaycaFe</title>


<script type='application/ld+json'>{"@context":"https://schema.org","@type":"WebSite","@id":"https://atelierrichelieu.com/#website","url":"https://atelierrichelieu.com/","name":"Atelier Richelieu","potentialAction":{"@type":"SearchAction","target":"https://atelierrichelieu.com/?s={search_term_string}","query-input":"required name=search_term_string"}}</script>
<link rel='dns-prefetch' href='//maps.googleapis.com' />
<link rel='stylesheet' id='wp-block-library-css'
   href='https://atelierrichelieu.com/wp-includes/css/dist/block-library/style.min.css'
   type='text/css' media='all' />
<link rel='stylesheet' id='styles-css'
   href='https://atelierrichelieu.com/wp-content/cache/busting/1/wp-content/themes/richelieu/assets/stylesheets/app.min-1.1.css'
   type='text/css' media='all' />
<link rel='https://api.w.org/'
   href='https://atelierrichelieu.com/wp-json/' />
<link rel="EditURI" type="application/rsd+xml" title="RSD"
   href="https://atelierrichelieu.com/xmlrpc.php?rsd" />
<meta name="generator" content="WPML ver:4.0.3 stt:1,4;" />

<link rel="icon" href="./img/coffee-icon1.png"
   type="image/png"/>
   

   
   <script type='text/javascript'
      src='https://atelierrichelieu.com/wp-content/cache/busting/1/wp-content/themes/richelieu/assets/javascripts/vendors.min-1.1.js'
      ></script>
   <script type='text/javascript'>
      var richelieu = {
         "url" : "https:\/\/atelierrichelieu.com",
         "theme" : "https:\/\/atelierrichelieu.com\/wp-content\/themes\/richelieu",
         "img" : "https:\/\/atelierrichelieu.com\/wp-content\/themes\/richelieu\/assets\/img\/",
         "ajaxurl" : "https:\/\/atelierrichelieu.com\/wp-admin\/admin-ajax.php",
         "host" : "atelierrichelieu.com"
      };
   </script>
   <script type='text/javascript'
      src='https://atelierrichelieu.com/wp-content/cache/busting/1/wp-content/themes/richelieu/assets/javascripts/app.min-1.1.js'
      defer></script>
   <script type='text/javascript'
      src='https://maps.googleapis.com/maps/api/js?key=AIzaSyD2X6kPzBfMY_oyHnCXeu7O-fMUhQlTYDk&#038;ver=5.0.4'></script>

</head>
<body>
<tiles:insertAttribute name="header"/>
<tiles:insertAttribute name="main"/> <!-- header footer은 같고 main은 다름 -->

</body>
</html>
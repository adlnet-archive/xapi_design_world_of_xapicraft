<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Send Statement In LRS</title>
        <script type='text/javascript' src='http://cdn4.tincanapi.com/wp-includes/js/jquery/jquery.js'></script>
        <script type="text/javascript">
        <!-- ValidateJSON from validate text -->
        function validateJSON(noisy) {
        var jsonString = document.getElementById("jsonText").value;
	var obj;
	var arr = [];
	try {
		obj = jQuery.parseJSON(jsonString);
	} catch(e) {
		alert("An error has occured during parsing: " + e.message);
		return false;
	}
	if (obj == null) {
		alert("There is no data to validate.");
		return false;
	}
	if (obj.verb == undefined) {
		alert("Verb field is required.");
		return false;
	}
	if (obj.actor == undefined || obj.actor.mbox == undefined || obj.actor.objectType == undefined) {
		alert("Actor must be defined with mbox field and objectType.");
		return false;
	}
	// Validate Verb
	if (!validateUri(obj.verb.id)) {
		alert("Verb " + obj.verb.id + " is not a valid uri");
		return false;
	}
	if (obj.verb.id === "http://adlnet.gov/expapi/verbs/voided") {
		alert("Use of the reserved verb voided is forbidden in the Statement Generator.");
		return false;
	}
	// Validate ActivityID
	if (obj.object == undefined) {
		alert("Statement object must be defined.");
		return false;
	}
	if (obj.object.id == undefined) {
		alert("Statement object id must be defined.");
		return false;
	}
	if (!validateUri(obj.object.id)) {
		alert("ActivityID " + obj.object.id + " is not a valid URI.");
		return false;
	}
	if (!validateEmail(String(obj.actor.mbox))) {
		alert("E-Mail " + obj.actor.mbox + " is not a valid email address.");
		return false;
	}
	// Validate object definition if it exists
	if (obj.object.definition != null) {
		// Create an array of locales to validate all names have a matching description
		// Validate locale for name
		for (var k in obj.object.definition.name) { // Easy way to get the key map
			if (!validateLocale(k)){
				alert("Locale " + k + " for object.definition.name is not a valid locale.");
				return false;
			}
			arr.push(k);
		}
		if (obj.object.definition.description != null) {
			// Validate locale for description
			var matches = 0;
			for (var k in obj.object.definition.description) {
				if (!validateLocale(k)){
					alert("Locale " + k + " for object.definition.description is not a valid locale.");
					return false;
				}
				if (jQuery.inArray(k, arr) == -1) {
					alert("Description with locale " + k + " does not have a matching name.");
					return false;
				}
				matches++;
			}
			// Validate both locales are the same
			if (matches !== arr.length) {
				alert("Name contains a locale definition not found in description.");
				return false;
			}
		}
	}
	// End validation
	if (noisy) {
		alert("Basic statement validation completed successfully.");
	}
	return true;
}
        </script>
        
        
    </head>
    <body>
  <% String retrieveData= (String)request.getAttribute("jsonString"); %>
      
  <form  action="save.do" method="post">
  <table width="745" height="536" border="0" align="center">
  <tr>
      <td width="145" height="339"> <u>JSON Statement</u>  </td>
    <td width="590"><textarea name="jsontext" id="jsonText" cols="80" rows="20" ></textarea></td>
  </tr>
  <tr>
    <td height="20">&nbsp;</td>
    <td align="center"><input type="Submit" onClick="return validateJSON(1)" name="postjson" value="Validate & Post JSON" /></td>
  </tr>
 
  <tr>
      <td height="163"><u>Result</u>  </td>
    <% //System.out.println(retrieveData); %>
    <td><%=retrieveData%></td>
  </tr>
</table>
</form>
        
        
    </body>
</html>

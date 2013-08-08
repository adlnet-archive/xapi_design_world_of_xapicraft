xapi_design_world_of_xapicraft
----------

## Team World of xAPICraft ##

1. Ali	Shahrazad (AS)
2. Jason	Smalley (JS)
3. Maribel	Nabong-Davis (MND)
4. Dave	Smith (DS)
5. Ellen	Meiselman (EM)
6. Erick	Emde (EE)
7. Milan	Hatch (MH)
8. Violeta	Gabriela (VG)

# Use Case #
## Use Case Name: ##
An HTML template for quick learning modules using the xAPI for tracking with<sup>1</sup> a server-side component for communicating LRS credentials.

## Actor(s): ##
Learners, Instructional Designers (IDs), learning technologists/developers, programmers.
## Use Case Description: ##
### Overview: ###
- Learning experiences occur everywhere, on distributed systems using a variety of tool providers<sup>2</sup>.

### Problem statement: ###
- SCORM content<sup>3</sup> requires that content is located in the same domain as the LMS/CMS. Externally hosted learning modules/experiences cannot easily be created and delivered to users who are identified in distributed platforms like an LMS/CMS). In some cases, a central platform for user and module management is not even present. 

### Proposed approach: ###
- Leverage the Experience API to track cross-domain learning experiences from an HTML template for tracking in an LRS. A server-side component with a configuration file/page will store and transmit Basic/OAuth provider credentials to an LRS endpoint (which is also configurable). Initially, an end user will manually enter their user credentials (OpenID, account, or email address) for identification and tracking purposes. Future iterations may include the use of LTI to connect the externally hosted learning module to a platform like an LMS or CMS for user identification and “launching” (presenting the content to a user in an LTI-enabled platform in the form of a shortcode/link or somesuch)<sup>4</sup>.

### Desired Outcome: ###
- The product of the work related to meet the needs of this use case will include a standard HTML learning module template, a server-side proxy/component for transmitting LRS credentials, and a method for constructing and sending xAPI statements for storage in an LRS (possibly with a configuration file). The initial outcome will not include methods for user identification and “launch” on an external platform (LMS/CMS).

## Preconditions: ##
### Assumptions: ###
1. The team developers/designers have HTML5 expertise to create the xAPI statement geneartion functions, or to leverage existing xAPI javascript libraries.
2. The ADL Tech team (Aaron and Andy) can provide additional functional testers as needed? As well as guidance for signoff, packaging (of product) and any maintenance support (?). 
3. Get access to an LRS for testing purposes. Setup a test LRS site.
4. Determine specific responsibilities of developers and designers for weekly sprints.

### Special Requirements: ###
- TBD

## Postconditions: ##
- TBD

## Normal Course of Events: ##

## Design Implications: ##

## Notes and Issues: ##

1. (EM) and possibly LTI<br>
   (AS) do you think its too aggressive for our schedule?<br>
   (EM) yes, I was modifying her text - she had LTI in there as a definite, and I'm not sure its worth it for this project, so I wrote "possibly"<br>
   (AS) Thoughts on the re-wording?<br>
   (MND) I'm glad to see these comments - if what I initially drafted does not make sense, feel free to make direct change on the use case. Thanks
2. (AS) not sure what else to include in the overview without talking about the problem statement. Please add...<br>
3. (EM) Possible problem statement: The SCORM standard requires that content be located in the same domain as the LMS. Sometimes this is not possible or desirable.<br>
   (AS) Added some wording to the problem statement as recommended. Please adjust as necessary.
4. (EM) Leverage the xAPI standard which does not have the same-domain limitation, to track cross-domain content. The suggested project would be a simple HTML template example, with any javascripts required or possibly a server-side proxy if needed for provider authentication to the LRS. Leverage existing code, such as the TinCan.js library as much as possible<br>
    (AS) Thanks. I gave this a stab, please provide feedback and make changes as necessary.<br>
    (MND) Proposed approach looks good. I have deleted SCORM content assumption and report format assumption. Do we keep the ADL tech team assumption? Also, are you all comfortable with scope of the project?


   
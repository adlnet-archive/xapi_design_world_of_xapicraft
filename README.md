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
Using xAPI<sup>1</sup> to Record the Results of  an HTML-based Learning Module 

## Actor(s): ##
Learners, Instructional Designers (IDs), learning technologists/developers, programmers.
## Use Case Description: ##
### Overview: ###
- Learning experiences occur everywhere, on distributed systems using a variety of tool providers. Many training departments use HTML as an inexpensive, flexible, and ubiquitous approach to delivery online training. Our project will result in an easy-to-use xAPI enabled HTML template. It will provide different options for storing activity specific data resulting from experiences with the HTML template and a way to communicate provider credentials in a secure manner.  

### Problem statement: ###
- Learning or training content requires that content is located in the same domain as the LMS/CMS2. Externally hosted learning modules/experiences cannot easily be created and delivered to users who are identified in distributed platforms like an LMS/CMS). In some cases, a central platform for user and module management is not even present.  

### Proposed approach: ###
- Leverage the Experience API to track cross-domain learning experiences from an HTML template for tracking in an LRS. A server-side component with a configuration file/page will store and transmit Basic/OAuth provider credentials to an LRS endpoint (which is also configurable). Initially, an end user will manually enter their user credentials (OpenID, account, or email address) for identification and tracking purposes. Future iterations may include the use of LTI to connect the externally hosted learning module to a platform like an LMS or CMS for user identification and “launching” (presenting the content to a user in an LTI-enabled platform in the form of a short code/link or somesuch)<sup>3</sup>.

### Desired Outcome: ###
- The product of the work related to meet the needs of this use case will include a standard HTML learning module template, a server-side proxy/component for transmitting LRS credentials, and a method for constructing and sending xAPI statements for storage in an LRS (possibly with a configuration file). The initial outcome will not include methods for user identification and “launch” on an external platform (LMS/CMS).

## Preconditions: ##
### Assumptions: ###
1. The team developers/designers have the expertise to construct and generate xAPI statements, leveraging existing open source xAPI libraries.  
2. The ADL Tech team (Aaron and Andy) can provide additional functional testers as needed, as well as any guidance for signoff, packaging (of product) and next steps following product delivery.  
3. The team has access to an LRS for testing purposes and can setup a test LRS site
4. Determine specific responsibilities of developers and designers for weekly sprints.
5. Team developers need to setup their GitHub profiles and local repositories for collaborative development and codebase revision control.
6. Decide how the agent information is obtained, communicated to the server-side, where the statement is constructed.
7. Decide on the method for the user to configure specific LRS settings on the server-side (UI? File?).
8. Decide the properties to include in the xAPI statements and how to provide a way to map HTML form/div elements to statement properties (using result, context, grouping, instructor, etc).
9. Decide what verbs (definitions) to use and the respective URIs from an existing registry if possible.

### Special Requirements: ###
- TBD

## Postconditions: ##
1. The HTML template will generate xAPI statements...
2. The HTML can be customized, deployed and it will...
3. Will this be open sourced? (I hope so)
4. Will we provide a demo<sup>4</sup>?  

## Normal Course of Events<sup>5</sup>: ##

## Design Implications: ##

## Notes and Issues: ##

1. (EM) and possibly LTI<br>
   (AS) do you think its too aggressive for our schedule?<br>
   (EM) yes, I was modifying her text - she had LTI in there as a definite, and I'm not sure its worth it for this project, so I wrote "possibly"<br>
   (AS) Thoughts on the re-wording?<br>
   (MND) I'm glad to see these comments - if what I initially drafted does not make sense, feel free to make direct change on the use case. Thanks
2. (EM) Possible problem statement: The SCORM standard requires that content be located in the same domain as the LMS. Sometimes this is not possible or desirable.<br>
   (AS) Added some wording to the problem statement as recommended. Please adjust as necessary.<br>
   (DS) I like the rewording by Ali that removes the reference to SCORM.
3. (EM) Leverage the xAPI standard which does not have the same-domain limitation, to track cross-domain content. The suggested project would be a simple HTML template example, with any javascripts required or possibly a server-side proxy if needed for provider authentication to the LRS. Leverage existing code, such as the TinCan.js library as much as possible<br>
    (AS) Thanks. I gave this a stab, please provide feedback and make changes as necessary.<br>
    (MND) Proposed approach looks good. I have deleted SCORM content assumption and report format assumption. Do we keep the ADL tech team assumption? Also, are you all comfortable with scope of the project?
4.	(AS) Ellen, Erick, Dave, please add to this section as I'm not really sure what to include here. Seems redundant since we have the desired outcome...?
5.	(AS) For this section I strongly believe we should describe how an experience relates to learning events on the "HTML learning module" and how that translates to an xAPI statement. Experience, Event, Statement. A non-technical high-level description of the course of events from the "user" and "designer" standpoint.<br>
(MND) Yes agreed. I'll take a stab at it from big picture POV.



   
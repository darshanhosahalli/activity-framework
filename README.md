# activity-framework
The Activity Framework Library is a flexible tool that enables users to plug in custom implementations before and after API calls, based on specific activities annotated in the code. This framework allows developers to define behaviors tied to different activities, facilitating tailored actions and responses around API interactions without altering the core logic of the application.



Note:- the Activity annotation if applied on a method that is called by an other method of the same class then the activity middleware is not executed since the Activity framework depends on Spring AOP proxies, When a method is called from a method within the same class then the Proxy is by-passed.

Note:- we cannot use @Activity on a method that is called invoked by the same class.
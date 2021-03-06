StrutsTestCase for TestNG - release 2.2.0
-----------------------------------------

This is a modification by [Sebastian Audet](mailto:smaudet@my.trine.edu) to 
StrutsTestCase to allow testing Struts 1 code using
the TestNG framework, instead of relying upon the old JUnit framework.

Since this relied upon the Cactus framework, which is both no longer 
supported, and relies exclusively upon Junit to work, support for Cactus has 
been removed. It may be added again if the need arises for it, 
and someone ports Cactus to TestNG.
 
Note - this is *NOT* a framework for Struts 2 - there is already a perfectly 
good framework for Struts 2 and TestNG which can be found [here]
(http://struts.apache.org/release/2.1.x/docs/testng-plugin.html)

Old Doc
-------

This is (was) a point release to add support for Struts 1.3. Tests written
for Struts 1.2 apps that have since been deployed to 1.3 may be tested
under 1.3 with this release. 

This release does *NOT* support configurations that use the
ComposableRequestProcessor new to Struts 1.3 -- only the 1.2
RequestProcessor/TilesRequestProcessor or their subclasses. Full-fledged
support for 1.3-style applications will be offered in a future release.

The StrutsTestCase library is available as three distinct
releases supporting the 2.2, 2.3, and 2.4 versions of the 
Servlet specification, and the 1.2 (and 1.3) versions of the Struts
Framework. PLEASE NOTE that StrutsTestCase no longer supports
the Struts 1.0 release.

[Deryl Seale and Ben Speakmon](http://strutstestcase.sourceforge.net)
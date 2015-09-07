Coevolution
===========

This component implements the GeoKnow Co-Evolution Services (Task T4.3). They are provided as a Java WAR, but can also be run as a standalone jar with built-in Jetty application server. For further documentation, see the deliverable 4.3.1 at http://geoknow.eu/Deliverables.html .

For legal reasons, this repository contains the interfaces (coevolution-core module) and the binaries (as WAR file). The source code is managed in a private repository.

##Building

Not applicable.

##Configuration

By default, Coevolution expects a local Virtuoso with a configured GeoKnow Generator (installed via the respective Debian package). If your setup varies, you can specify different properties at /etc/tomcat7/Catalina/localhost/coevolution-service.xml configuration file. By default, this context is configured to be reloadable, so the coevolution webapp should restart after saving changes there.

##Starting

Copy the WAR file to your local application server's webapp directory. Start the server and see, e.g.,

	http://localhost:8080/coevolution-service/
	
##Use

See GeoKnow Deliverable D4.3.1 and D4.3.2 for available services and current implementation.

In addition to the state specified there, since version 0.2.0 Co-Evolution Services provide change tracking functionality during the change application process. The /application/graph resource returns a ChangeTracking instance with basic statistics, including a change application URI denoting a resource at the change tracking graph http://generator.geoknow.eu/resource/changetracking (defined in the Namespace class) which includes further details, e.g., the configured correction context and target graph, process start time, (partially) applied or ignored change requests, additions, and removals. For further details see the ChangeTrackingVocabulary, ChangeTracking, and ChangeTrackingDao classes.

Also, 0.2.0 added a working /sync/diff implementation with full JSON/XML/Turtle support. You can define two graphs and Co-Evolution Sync Service will auto-generate a list of change requests for the differences between these graphs.

Version 0.2.1 added suffix-based content negotiation. You can now use the common file endings .json, .xml, and .ttl to specify the required content type instead of providing a HTTP Accept request header. For example, this can be used to import a sync diff from another GeoKnow Generator instance with Co-Evolution Services directly into a graph by choosing "Import RDF Data" from "URL" in Generator with the following source URL for source and target graphs ``urn:g1`` and ``urn:g2``:

	http://REMOTE_GENERATOR:PORT/coevolution-service/rest/api/sync/diff.ttl?source=urn%3Ag1&target=urn%3Ag2
	
##Licence

For use outside of the GeoKnow Generator demonstrator, please contact matthias.wauer@unister.de .
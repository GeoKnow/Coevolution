package com.unister.semweb.geoknow.coevolution.providers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;

/**
 * This class is an provider which provide mapping of Java types and representations (Turtle only).
 * This provider accepts and produces Turtle using OpenRDF RIO.
 */
@Component
@Provider
@Produces({ TurtleProvider.TURTLE_MEDIA_TYPE })
@Consumes({ TurtleProvider.TURTLE_MEDIA_TYPE })
public class TurtleProvider implements MessageBodyReader<Object>, MessageBodyWriter<Object> {

    public static final String TURTLE_MEDIA_TYPE = "text/turtle";
    
    public TurtleProvider() {
    }
    
    @Override
    public boolean isReadable(Class<?> arg0, Type arg1, Annotation[] arg2,
            MediaType arg3) {
        if (arg3.isCompatible(MediaType.APPLICATION_XML_TYPE))
            return true;
        else
            return false;
    }

    @Override
    public Object readFrom(Class<Object> arg0, Type arg1, Annotation[] arg2,
            MediaType arg3, MultivaluedMap<String, String> arg4,
            InputStream arg5) throws IOException, WebApplicationException {
        if (arg3.isCompatible(MediaType.APPLICATION_XML_TYPE)) {
//            return xStream.fromXML(arg5);
            return null;
        } else {
            throw new WebApplicationException(Response.Status.UNSUPPORTED_MEDIA_TYPE);
        }
    }

    @Override
    public long getSize(Object arg0, Class<?> arg1, Type arg2,
            Annotation[] arg3, MediaType arg4) {
//        long l = xStream.toXML(arg0).length();
//        System.out.println(l);        
//        return l;
        return 0;
    }

    @Override
    public boolean isWriteable(Class<?> arg0, Type arg1, Annotation[] arg2,
            MediaType arg3) {
        if (arg3.equals(MediaType.APPLICATION_XML_TYPE))
            return true;
        else
            return false;
    }

    @Override
    public void writeTo(Object arg0, Class<?> arg1, Type arg2,
            Annotation[] arg3, MediaType arg4,
            MultivaluedMap<String, Object> arg5, OutputStream arg6)
            throws IOException, WebApplicationException {
        if (arg4.equals(MediaType.APPLICATION_XML_TYPE)) {
//            arg6.write(xStream.toXML(arg0).getBytes());
            arg6.flush();
        } else {
            System.out.println(arg0);
            throw new WebApplicationException(Response.Status.UNSUPPORTED_MEDIA_TYPE);
        }
    }

}
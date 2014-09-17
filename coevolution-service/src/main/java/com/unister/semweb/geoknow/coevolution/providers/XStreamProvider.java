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

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * This class is an provider which provide mapping of Java types and representations (XML and JSON).
 * This provider accepts and produces XML and JSON using XStream.
 */
@Provider
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
public class XStreamProvider implements MessageBodyReader<Object>, MessageBodyWriter<Object> {

    @Override
    public boolean isReadable(Class<?> arg0, Type arg1, Annotation[] arg2,
            MediaType arg3) {
        // isCompatible should be used instead of equals if parameters like encoding or locale does not matter.
        if (arg3.isCompatible(MediaType.APPLICATION_JSON_TYPE) || arg3.isCompatible(MediaType.APPLICATION_XML_TYPE))
            return true;
        else
            return false;
    }

    @Override
    public Object readFrom(Class<Object> arg0, Type arg1, Annotation[] arg2,
            MediaType arg3, MultivaluedMap<String, String> arg4,
            InputStream arg5) throws IOException, WebApplicationException {
        if (arg3.equals(MediaType.APPLICATION_JSON_TYPE)) {
            XStream stream = new XStream(new JsonHierarchicalStreamDriver());
            stream.autodetectAnnotations(true);
            return stream.fromXML(arg5);
        } else if (arg3.isCompatible(MediaType.APPLICATION_XML_TYPE)) {
            XStream stream = new XStream(new DomDriver());
            return stream.fromXML(arg5);
        } else {
            throw new WebApplicationException(Response.Status.UNSUPPORTED_MEDIA_TYPE);
        }
    }

    @Override
    public long getSize(Object arg0, Class<?> arg1, Type arg2,
            Annotation[] arg3, MediaType arg4) {
        XStream stream = null;
        if (arg4.equals(MediaType.APPLICATION_JSON_TYPE)) {
            stream = new XStream(new JsonHierarchicalStreamDriver());
        } else {
            stream = new XStream(new DomDriver());
            stream.autodetectAnnotations(true);            
        }
        long l = stream.toXML(arg0).length();
        System.out.println(l);
        return l;
    }

    @Override
    public boolean isWriteable(Class<?> arg0, Type arg1, Annotation[] arg2,
            MediaType arg3) {
        if (arg3.equals(MediaType.APPLICATION_JSON_TYPE) || arg3.equals(MediaType.APPLICATION_XML_TYPE))
            return true;
        else
            return false;
    }

    @Override
    public void writeTo(Object arg0, Class<?> arg1, Type arg2,
            Annotation[] arg3, MediaType arg4,
            MultivaluedMap<String, Object> arg5, OutputStream arg6)
            throws IOException, WebApplicationException {
        if (arg4.equals(MediaType.APPLICATION_JSON_TYPE)) {
            XStream stream = new XStream(new JsonHierarchicalStreamDriver());
            arg6.write(stream.toXML(arg0).getBytes());
            arg6.flush();
        } else if (arg4.equals(MediaType.APPLICATION_XML_TYPE)) {
            XStream stream = new XStream(new DomDriver());
            stream.autodetectAnnotations(true);            
            arg6.write(stream.toXML(arg0).getBytes());
            arg6.flush();
        } else {
            System.out.println(arg0);
            throw new WebApplicationException(Response.Status.UNSUPPORTED_MEDIA_TYPE);
        }
    }
}
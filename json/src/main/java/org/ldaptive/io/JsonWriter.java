/* See LICENSE for licensing and NOTICE for copyright. */
package org.ldaptive.io;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONValue;
import org.ldaptive.LdapAttribute;
import org.ldaptive.LdapEntry;
import org.ldaptive.SearchResult;

/**
 * Writes a {@link SearchResult} as JSON to a {@link Writer}.
 *
 * @author  Middleware Services
 */
public class JsonWriter implements SearchResultWriter
{

  /** Writer to write to. */
  private final Writer jsonWriter;


  /**
   * Creates a new json writer.
   *
   * @param  writer  to write JSON to
   */
  public JsonWriter(final Writer writer)
  {
    jsonWriter = writer;
  }


  /**
   * Writes the supplied search result to the writer.
   *
   * @param  result  search result to write
   *
   * @throws  IOException  if an error occurs using the writer
   */
  @Override
  public void write(final SearchResult result)
    throws IOException
  {
    final List<Map<String, Object>> json = new ArrayList<>();
    for (LdapEntry e : result.getEntries()) {
      final Map<String, Object> jsonEntry = new LinkedHashMap<>();
      final String dn = e.getDn();
      if (dn != null) {
        jsonEntry.put("dn", e.getDn());
      }
      for (LdapAttribute a : e.getAttributes()) {
        final String name = a.getName();
        final List<String> l = new ArrayList<>();
        l.addAll(a.getStringValues());
        jsonEntry.put(name, l);
      }
      json.add(jsonEntry);
    }
    JSONValue.writeJSONString(json, jsonWriter);
    jsonWriter.flush();
  }
}

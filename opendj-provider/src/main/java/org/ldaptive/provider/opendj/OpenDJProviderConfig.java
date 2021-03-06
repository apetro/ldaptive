/* See LICENSE for licensing and NOTICE for copyright. */
package org.ldaptive.provider.opendj;

import java.util.Arrays;
import org.forgerock.opendj.ldap.LDAPOptions;
import org.forgerock.opendj.ldap.controls.Control;
import org.ldaptive.ResultCode;
import org.ldaptive.provider.ControlProcessor;
import org.ldaptive.provider.ProviderConfig;

/**
 * Contains configuration data for the OpenDJ provider.
 *
 * @author  Middleware Services
 */
public class OpenDJProviderConfig extends ProviderConfig<Control>
{

  /** Connection options. */
  private LDAPOptions options;

  /** Search result codes to ignore. */
  private ResultCode[] searchIgnoreResultCodes;


  /** Default constructor. */
  public OpenDJProviderConfig()
  {
    setOperationExceptionResultCodes(ResultCode.SERVER_DOWN);
    setControlProcessor(new ControlProcessor<>(new OpenDJControlHandler()));
    searchIgnoreResultCodes = new ResultCode[] {
      ResultCode.TIME_LIMIT_EXCEEDED,
      ResultCode.SIZE_LIMIT_EXCEEDED,
      ResultCode.REFERRAL,
    };
  }


  /**
   * Returns the connection options.
   *
   * @return  ldap options
   */
  public LDAPOptions getOptions()
  {
    return options;
  }


  /**
   * Sets the connection options.
   *
   * @param  o  ldap options
   */
  public void setOptions(final LDAPOptions o)
  {
    options = o;
  }


  /**
   * Returns the search ignore result codes.
   *
   * @return  result codes to ignore
   */
  public ResultCode[] getSearchIgnoreResultCodes()
  {
    return searchIgnoreResultCodes;
  }


  /**
   * Sets the search ignore result codes.
   *
   * @param  codes  to ignore
   */
  public void setSearchIgnoreResultCodes(final ResultCode[] codes)
  {
    checkImmutable();
    logger.trace("setting searchIgnoreResultCodes: {}", Arrays.toString(codes));
    searchIgnoreResultCodes = codes;
  }


  @Override
  public String toString()
  {
    return
      String.format(
        "[%s@%d::operationExceptionResultCodes=%s, properties=%s, " +
        "connectionStrategy=%s, controlProcessor=%s, options=%s, " +
        "searchIgnoreResultCodes=%s]",
        getClass().getName(),
        hashCode(),
        Arrays.toString(getOperationExceptionResultCodes()),
        getProperties(),
        getConnectionStrategy(),
        getControlProcessor(),
        options,
        Arrays.toString(searchIgnoreResultCodes));
  }
}

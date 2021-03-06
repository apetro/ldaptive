/* See LICENSE for licensing and NOTICE for copyright. */
package org.ldaptive.beans;

import java.util.Collection;

/**
 * Describes the attribute value mutators and DN value mutators for a specific type.
 *
 * @author  Middleware Services
 */
public interface ClassDescriptor
{


  /**
   * Prepare this class descriptor for use.
   *
   * @param  type  of object to describe
   */
  void initialize(Class<?> type);


  /**
   * Returns the DN value mutator for this type.
   *
   * @return  dn value mutator
   */
  DnValueMutator getDnValueMutator();


  /**
   * Returns the attribute value mutators for this type.
   *
   * @return  value mutators
   */
  Collection<AttributeValueMutator> getAttributeValueMutators();


  /**
   * Returns the attribute value mutator for the attribute with the supplied name.
   *
   * @param  name  of the attribute
   *
   * @return  value mutator
   */
  AttributeValueMutator getAttributeValueMutator(String name);
}

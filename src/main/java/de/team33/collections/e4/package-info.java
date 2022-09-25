/**
 * This library or this package represents an alternative design to the Java Collections library.
 * The focus here is on the following aspects:
 *
 * <ul>
 *     <li>The classes defined here represent either immutable data objects with value semantics or tools for building
 *     such objects. Not both at the same time.</li>
 *     <li>The definition of optional, technically motivated but actually unnecessary exceptions is avoided,
 *     such as the possibility of a {@link java.lang.NullPointerException} on
 *     {@link java.util.Collection#contains(java.lang.Object)} when using a {@code null} argument.</li>
 *     <li>The element-by-element processing of a collection takes place exclusively via streams.</li>
 * </ul>
 */
package de.team33.collections.e4;

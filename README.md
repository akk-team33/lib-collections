# lib-collections

This library represents an alternative design to the Java Collections library.
The focus here is on the following aspects:

- The classes defined here represent either immutable data objects with value semantics or tools for building such 
objects. Not both at the same time.
- The definition of optional, technically motivated but actually unnecessary exceptions is avoided, such as the
possibility of a NullPointerException on contains(Object) when using a null argument.
- The element-by-element processing of a collection takes place exclusively via streams.

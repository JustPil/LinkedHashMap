# HashMapDoublyLinked
Summary

Hash Map ADT with a Linked List for collision resolution.
The internal Linked List additionally preserves the insertion order of elements.

Design

Supports holding key/value pair elements, addition and removal of elements, element search, and other features. Internal storage of elements uses an array of objects with variables for key, value, and references. Includes a default capacity or user-defined capacity.

The Linked List nodes have references for a 'forward list' which preserves insertion order of all elements, and a 'backward list' used exclusively for collision handling. Insertion order may be requested by the user and is returned in the form of an array of keys to prevent unauthorized access directly to the Linked List's nodes.

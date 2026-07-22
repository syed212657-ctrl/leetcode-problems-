/**
 * @param {any} obj
 * @param {any} classFunction
 * @return {boolean}
 */
var checkIfInstanceOf = function(obj, classFunction) {
    // Edge case: If obj is null/undefined or classFunction isn't a valid function/class, return false
    if (obj === null || obj === undefined || typeof classFunction !== 'function') {
        return false;
    }

    // Get the prototype of the object. 
    // Object() turns primitives (like 5 or "hello") into their object wrapper equivalents.
    let currentProto = Object.getPrototypeOf(Object(obj));

    // Traverse up the prototype chain
    while (currentProto !== null) {
        // If the prototype matches the class's prototype, it's an instance
        if (currentProto === classFunction.prototype) {
            return true;
        }
        // Move up to the next link in the prototype chain
        currentProto = Object.getPrototypeOf(currentProto);
    }

    return false;
};

// --- Test Cases ---
console.log(checkIfInstanceOf(new Date(), Date)); // true
console.log(checkIfInstanceOf(5, Number));         // true (Handles primitive)
console.log(checkIfInstanceOf("hello", String));   // true (Handles primitive)
class Animal {}
class Dog extends Animal {}
console.log(checkIfInstanceOf(new Dog(), Animal)); // true (Handles inheritance)
console.log(checkIfInstanceOf(null, Object));      // false

/**
 * checkIfInstanceOf(new Date(), Date); // true
 */
 

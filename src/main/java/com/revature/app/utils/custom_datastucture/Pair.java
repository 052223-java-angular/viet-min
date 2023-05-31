/**
 * A custom data structure class that represents a pair of a string and an integer.
 * It uses lombok annotations to generate constructors, getters, setters and toString methods.
 * It has fields for the string id and the integer count.
 */
package com.revature.app.utils.custom_datastucture;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Pair {
    String id;
    int count;
}
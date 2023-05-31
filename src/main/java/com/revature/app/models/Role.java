/**
 * A class that represents a role for a user in an online store.
 * It has an id and a name.
 */
package com.revature.app.models;

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
public class Role {
    /**
     * The id of the role.
     */
    private String id;
    /**
     * The name of the role, such as "admin" or "customer".
     */
    private String name;
}
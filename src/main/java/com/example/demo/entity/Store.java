// package com.example.demo.entity;

// import jakarta.persistence.*;

// @Entity
// @Table(name = "stores")
// public class Store {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @Column(nullable = false)
//     private String storeName;

//     private String address;

//     private String region;

//     @Column(nullable = false)
//     private boolean active = true; // DEFAULT TRUE (TESTED)

    

//     public Store() {
//         // default constructor
//     }

   

//     public Long getId() {
//         return id;
//     }

//     public void setId(Long id) {
//         this.id = id;
//     }

//     public String getStoreName() {
//         return storeName;
//     }

//     public void setStoreName(String storeName) {
//         this.storeName = storeName;
//     }

//     public String getAddress() {
//         return address;
//     }
 
//     public void setAddress(String address) {
//         this.address = address;
//     }
 
//     public String getRegion() {
//         return region;
//     }
 
//     public void setRegion(String region) {
//         this.region = region;
//     }
 
//     public boolean isActive() {
//         return active;
//     }
 
//     public void setActive(boolean active) {
//         this.active = active;
//     }
// }
package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "stores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String storeName;

    private String address;
    private String region;

    @Builder.Default
    private boolean active = true;
}

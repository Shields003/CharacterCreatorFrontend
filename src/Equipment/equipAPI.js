import React, { useState, useEffect } from "react";
import { random } from "superheroes";

//This code retrieves equipment from http://www.dnd5eapi.co/docs/#get-/api/equipment/-index- and displays it in a table.
// Path: src/Functions/equipAPI.js
//Get a random item from the equipment list

const getRandEquipAPI = async () => {
     const response = await fetch("http://www.dnd5eapi.co/api/equipment/");
     const data = await response.json();
     const rand = Math.floor(Math.random() * data.results.length);
     return data.results[rand];
     };
     console.log(getRandEquipAPI);

// Path: src/Functions/equipAPI.js
//Get a random item from the equipment list

const EquipAPI = () => {
  const [equip, setEquip] = useState([]);
  useEffect(() => {
    getEquip(random);
  }, []);
  const getEquip = async () => {
    const response = await fetch("http://www.dnd5eapi.co/api/equipment/");
    const data = await response.json();
    setEquip(data.results);
  };
  return (
    <div>
      <h1>Equipment</h1>
      <table>
        <thead>
          <tr>
            <th>Name</th>
            <th>Cost</th>
            <th>Weight</th>
            <th>Damage</th>
            <th>Properties</th>
            <th>Description</th>
          </tr>
        </thead>
        <tbody>
          {equip.map((item) => (
            <tr key={item.index}>
              <td>{item.name}</td>
              <td>{item.cost}</td>
              <td>{item.weight}</td>
              <td>{item.damage}</td>
              <td>{item.properties}</td>
              <td>{item.description}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};
console.log(EquipAPI);

export default EquipAPI;

import React, { useState } from "react";
import Box from "@mui/material/Box";
import InputLabel from "@mui/material/InputLabel";
import MenuItem from "@mui/material/MenuItem";
import FormControl from "@mui/material/FormControl";
import Select from "@mui/material/Select";
import { Card } from "@mui/material";

function WeaponSelect() {
  const weapon = [
    { Weapon: "Long Sword", id: 1 },
    { Weapon: "Staff", id: 2 },
    { Weapon: "Battle Axe", id: 3 },
    { Weapon: "Long Bow", id: 4 },
  ];
  const [options] = useState(weapon);
  return (
    <div style={{ width: "90%", justifyContent: "center", distplay: "flex" }}>
      <div className="WeaponSelect">
        <h3 style={{ color: "red" }}>Multiselect Dropdown useState</h3>
        <Select options={options} displayValue={weapon} />
      </div>
    </div>
  );
}

export default WeaponSelect;

import * as React from "react";
import InputLabel from "@mui/material/InputLabel";
import MenuItem from "@mui/material/MenuItem";
import FormControl from "@mui/material/FormControl";
import Select from "@mui/material/Select";
import Button from "@mui/material/Button";

export default function ControlledOpenSelect() {
  const [age, setAge] = React.useState("");
  const [open, setOpen] = React.useState(false);

  const handleChange = (event) => {
    setAge(event.target.value);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const handleOpen = () => {
    setOpen(true);
  };

  return (
    <div>
      <Button sx={{ display: "block", mt: 2 }} onClick={handleOpen}>
        Open the select
      </Button>
      <FormControl sx={{ m: 1, minWidth: 120 }}>
        <InputLabel id="demo-controlled-open-select-label">Age</InputLabel>
        <Select
          labelId="demo-controlled-open-select-label"
          id="demo-controlled-open-select"
          open={open}
          onClose={handleClose}
          onOpen={handleOpen}
          value={age}
          label="Weapons"
          onChange={handleChange}
        >
          <MenuItem value="">
            <em>None</em>
          </MenuItem>
          <MenuItem value={10}>Long Sword</MenuItem>
          <MenuItem value={20}>Staff</MenuItem>
          <MenuItem value={30}>Battle Axe</MenuItem>
          <MenuItem value={10}>Spear</MenuItem>
          <MenuItem value={20}>Short Sword</MenuItem>
          <MenuItem value={30}>Long Bow</MenuItem>
          <MenuItem value={10}>Mace</MenuItem>
          <MenuItem value={20}>Hand Axe</MenuItem>
          <MenuItem value={30}>Flail</MenuItem>
        </Select>
      </FormControl>
    </div>
  );
}

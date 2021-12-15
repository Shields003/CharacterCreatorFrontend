import React, { useState, useMemo } from "react";

const Usefield = () => {
  const [value, set_value] = useState("");

  const onChange = (event) => {
    set_value(event.target.value);
  };

  const first_char = useMemo(() => value[0], [value]);

  const logon = () => {
    console.log("logged on!");
  };
  return { value, onChange, first_char, logon };
};

export default Usefield;

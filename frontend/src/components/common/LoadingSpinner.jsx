import React from 'react';

export const LoadingSpinner = () => {
  return (
    <>
      <div className="sk-three-bounce">
        <div className="sk-child sk-bounce1"></div>
        <div className="sk-child sk-bounce2"></div>
        <div className="sk-child sk-bounce3"></div>
      </div>
    </>
  )
}

export default LoadingSpinner;
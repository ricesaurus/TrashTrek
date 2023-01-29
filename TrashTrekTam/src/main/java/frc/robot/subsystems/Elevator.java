// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxAlternateEncoder.Type;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxLimitSwitch;
import com.revrobotics.CANSparkMax.SoftLimitDirection;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Elevator extends SubsystemBase {
  //Objects
  CANSparkMax m_elevatorMotor = new CANSparkMax(0, MotorType.kBrushless);
  SparkMaxLimitSwitch m_limitSwitch = m_elevatorMotor.getForwardLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyOpen);
  RelativeEncoder m_elevatorEncoder = m_elevatorMotor.getAlternateEncoder(Type.kQuadrature,8192);



  /** Elevator */
  public Elevator() {
    
    m_elevatorMotor.enableSoftLimit(SoftLimitDirection.kForward, true);
    m_elevatorMotor.setSoftLimit(SoftLimitDirection.kForward, 0);

  }
  //Important Variable;
  public double elevatorMotorPosition = m_elevatorEncoder.getPosition()*m_elevatorEncoder.getPositionConversionFactor();
  //Methods
  //limitSwitch function = is it pressed or not
  public boolean checkLimitSwitch(){
    return m_limitSwitch.isPressed();
  }
  //Max Height Method, stops when hit LimitSwitch
  public void elevatorMaxHeight(){
    //Limit switch at top activated, then set speed 0
    while(checkLimitSwitch() == false){
      m_elevatorMotor.set(0.7);

    }
    m_elevatorMotor.set(0);

  }
  //Minimum Height Method
  public void elevatorMinHeight(){
    //While position of motor is greater than soft limit, elevator decreases
    while(elevatorMotorPosition > m_elevatorMotor.getSoftLimit(null)){
      m_elevatorMotor.set(-0.7);
    }
    m_elevatorMotor.set(0);
  }

  public void elevatorHalfHeight(){
    //If above, descend until less than or equal to 500
    if(elevatorMotorPosition>500){
      while(elevatorMotorPosition >= 500){
        m_elevatorMotor.set(-0.7);
      }
    }
    //If below, ascend until greater than or equal to 500
    if(elevatorMotorPosition<500){
      while(elevatorMotorPosition<=500){
        m_elevatorMotor.set(0.7);
      }

    }

  }

  //Half /500 Height Method
  //REVLib Error method??
  //REVLIBError?
  //import com.revrobotics.REVLibError?
  //REVLibError.kOk if successful
  




  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putBoolean("LimitSwitchStatus", m_elevatorMotor.isSoftLimitEnabled(null));
    //Multiplied getPosition() by getPositionConversionFactor() for the position?
    SmartDashboard.putNumber("Elevator Height", elevatorMotorPosition);
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}

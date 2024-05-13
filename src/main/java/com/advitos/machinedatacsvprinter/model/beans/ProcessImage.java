package com.advitos.machinedatacsvprinter.model.beans;


import com.advitos.machinedatacsvprinter.model.BinaryLabviewData;
import com.advitos.machinedatacsvprinter.model.SWVersion;
import io.netty.buffer.ByteBuf;
import jakarta.annotation.ManagedBean;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;


import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ProcessImage implements BinaryLabviewData {

    @Getter
    @Setter
    private List<NumericInput> numericInputs = new ArrayList<>();
    @Getter
    private List<DigitalInput> digitalInputs = new ArrayList<>();
    @Getter
    @Setter
    private List<ClosedLoop> closedLoops = new ArrayList<>();
    @Getter
    private List<NumericOutput> numericOutputs = new ArrayList<>();
    @Getter
    private List<DigitalOutput> digitalOutputs = new ArrayList<>();
    @Getter
    @Setter
    private IOData ioData = new IOData();
    @Getter
    @Setter
    private ProcessControlData processControlData = new ProcessControlData();
    private ProtectiveSystemData protectiveSystemData = new ProtectiveSystemData();
    private BalancingData balancingData = new BalancingData();
    private SystemDiagnosis systemDiagnosis = new SystemDiagnosis();
    @Getter
    @Setter
    private ErrorData errorData = new ErrorData();

    @Override
    public void parse(ByteBuf buffer, SWVersion version) {

        int numericInputsSize = buffer.readInt();
        if (numericInputsSize < 200) {
            for (int i = 0; i < numericInputsSize; i++) {
                NumericInput numericInput = new NumericInput();
                numericInput.parse(buffer, version);
                numericInputs.add(numericInput);
            }
        }
        int digitalInputsSize = buffer.readInt();
        if (digitalInputsSize < 200) {
            for (int i = 0; i < digitalInputsSize; i++) {
                DigitalInput digitalInput = new DigitalInput();
                digitalInput.parse(buffer, version);
                digitalInputs.add(digitalInput);
            }
        }
        int closedLoopsSize = buffer.readInt();
        if (closedLoopsSize < 200) {
            for (int i = 0; i < closedLoopsSize; i++) {
                ClosedLoop closedLoop = new ClosedLoop();
                closedLoop.parse(buffer, version);
                closedLoops.add(closedLoop);
            }
        }
        int numericOutputsSize = buffer.readInt();
        if (numericOutputsSize < 200) {
            for (int i = 0; i < numericOutputsSize; i++) {
                NumericOutput numericOutput = new NumericOutput();
                numericOutput.parse(buffer, version);
                numericOutputs.add(numericOutput);
            }
        }
        int digitalOutputsSize = buffer.readInt();
        if (digitalOutputsSize < 200) {
            for (int i = 0; i < digitalOutputsSize; i++) {
                DigitalOutput digitalOutput = new DigitalOutput();
                digitalOutput.parse(buffer, version);
                digitalOutputs.add(digitalOutput);
            }
        }

        ioData.parse(buffer, version);
        processControlData.parse(buffer, version);
        protectiveSystemData.parse(buffer, version);
        balancingData.parse(buffer, version);
        systemDiagnosis.parse(buffer, version);
        errorData.parse(buffer, version);
    }

}
